package cc.moecraft.icq.command;

import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.event.EventManager;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.utils.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandListener {
    private final EventManager eventManager;

    private final CommandManager commandManager;

    private final Map<String, Thread> runningAsyncThreads = new ConcurrentHashMap<>();

    public CommandListener(EventManager eventManager, CommandManager commandManager) {
        this.eventManager = eventManager;
        this.commandManager = commandManager;
    }

    /**
     * 检查并运行指令
     *
     * @param event 事件
     * @return 是否继续执行后续事件
     */
    public boolean check(EventMessage event) {
        CommandArgs args = parseCmdArgs(event);
        if (args == null) return true;
        CommandRunnable runnable = new CommandRunnable(event, args);
        if (PicqConfig.getInstance().isUseAsyncCommands()) {
            Thread thread = new Thread(runnable, "Thread-" + System.currentTimeMillis());
            final String threadName = thread.getName();
            runningAsyncThreads.put(threadName, thread);
            runnable.setCallback(() -> runningAsyncThreads.remove(threadName));
            thread.start();
        } else {
            runnable.run();
        }

        return PicqConfig.getInstance().isCommandsAlsoCallEvents();
    }

    private @Nullable CommandArgs parseCmdArgs(EventMessage event) {
        String fullCommand = StringUtils.trimLeft(event.getMessage());
        String prefix = getPrefix(commandManager.getPrefixes(), fullCommand);
        if (prefix == null) {
            if (event instanceof EventGroupMessage) {
                return null;
            }
            prefix = "";
        }
        fullCommand = StringUtils.trimLeft(fullCommand.substring(prefix.length()));
        // 因为如果最后全是空格的话split会忽略这些空格, 所以要先在结尾添加一个字符
        fullCommand += " ;";

        ArrayList<String> args = new ArrayList<>(Arrays.asList(
            fullCommand.split(PicqConfig.getInstance().getCommandArgsSplitRegex())
        ));

        // 移除结尾添加的字符
        args.remove(args.size() - 1);

        String command = args.get(0).toLowerCase();
        args.remove(0);

        if (!commandManager.getCommands().containsKey(command)) {
            return null;
        }
        return new CommandArgs(prefix, command, commandManager.getCommands().get(command), args);
    }

    private static @Nullable String getPrefix(String[] prefixes, String text) {
        for (String prefix : prefixes) {
            if (text.startsWith(prefix)) {
                return prefix;
            }
        }
        return null;
    }

    private class CommandRunnable implements Runnable {
        private final EventMessage event;
        private final CommandArgs args;
        private Runnable callback;

        public CommandRunnable(EventMessage event, CommandArgs args) {
            this.event = event;
            this.args = args;
        }

        @Override
        public void run() {
            try {
                commandManager.runCommand(event, args);
            } catch (Throwable e) {
                eventManager.callError(event, e);
                PicqConfig.getInstance().getCommandErrorHandler().accept(e);
            }
            if (callback != null) {
                callback.run();
            }
        }

        public EventMessage getEvent() {
            return event;
        }

        public CommandArgs getArgs() {
            return args;
        }

        public Runnable getCallback() {
            return callback;
        }

        public void setCallback(Runnable callback) {
            this.callback = callback;
        }
    }
}
