package cc.moecraft.icq.command;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.command.interfaces.GroupCommand;
import cc.moecraft.icq.command.interfaces.IcqCommand;
import cc.moecraft.icq.command.interfaces.PrivateCommand;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import cc.moecraft.icq.event.events.message.EventTempMessage;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final PicqBotX bot;

    /**
     * 指令前缀
     */
    private final String[] prefixes;

    /**
     * 已注册的指令, [指令名, 指令对象]
     */
    private final Map<String, IcqCommand> commands = new HashMap<>();

    /**
     * 构造一个指令管理器
     *
     * @param bot      机器人对象
     * @param prefixes 前缀
     */
    public CommandManager(PicqBotX bot, String... prefixes) {
        this.bot = bot;
        this.prefixes = prefixes;
    }

    /**
     * 注册多个指令
     *
     * @param commands 多个指令
     */
    public void registerCommands(IcqCommand... commands) {
        for (IcqCommand command : commands) {
            registerCommand(command);
        }
    }

    /**
     * 注册指令
     *
     * @param command 指令
     */
    public void registerCommand(IcqCommand command) {
        commands.put(command.properties().getName(), command);
        command.properties().getAlias().forEach(alias -> commands.put(alias.toLowerCase(), command));
    }

    public void runCommand(EventMessage event, CommandArgs args) {
        if (PicqConfig.getInstance().isMaintenanceMode()) {
            event.respond(PicqConfig.getInstance().getMaintenanceResponse());
            return;
        }

        IcqCommand runner = args.getCommandRunner();
        if (runner instanceof EverywhereCommand) {
            User user = event.getSender();
            event.respond(((EverywhereCommand) runner).run(
                event,
                user,
                args.getCommandName(),
                args.getArgs()
            ), true);
        } else if (
            event instanceof EventGroupMessage
                && runner instanceof GroupCommand
        ) {
            EventGroupMessage egm = (EventGroupMessage) event;
            event.respond(((GroupCommand) runner).groupMessage(
                egm,
                (Member) egm.getSender(),
                egm.getGroup(),
                args.getCommandName(),
                args.getArgs()
            ));
        } else if (runner instanceof PrivateCommand) {
            if (event instanceof EventPrivateMessage) {
                EventPrivateMessage epm = (EventPrivateMessage) event;
                event.respond(((PrivateCommand) runner).privateMessage(
                    epm,
                    (Friend) epm.getSender(),
                    args.getCommandName(),
                    args.getArgs()
                ));
            } else if (event instanceof EventTempMessage) {
                EventTempMessage etm = (EventTempMessage) event;
                event.respond(((PrivateCommand) runner).tempMessage(
                    etm,
                    (Member) etm.getSender(),
                    args.getCommandName(),
                    args.getArgs()
                ));
            }
        }
    }

    /**
     * 获取指令列表
     *
     * @return 指令列表
     */
    public ArrayList<IcqCommand> getCommandList() {
        ArrayList<IcqCommand> result = new ArrayList<>();
        commands.forEach((k, v) -> {
            if (!result.contains(v)) result.add(v);
        });
        return result;
    }

    /**
     * 获取指令名列表
     *
     * @return 指令名列表
     */
    public ArrayList<String> getCommandNameList() {
        return new ArrayList<>(commands.keySet());
    }

    public PicqBotX getBot() {
        return bot;
    }

    /**
     * @return 前缀
     */
    public String[] getPrefixes() {
        return prefixes;
    }

    /**
     * @return 已注册的所有指令
     */
    public Map<String, IcqCommand> getCommands() {
        return commands;
    }
}
