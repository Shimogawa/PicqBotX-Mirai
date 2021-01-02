package cc.moecraft.icq;

import cc.moecraft.icq.command.CommandListener;
import cc.moecraft.icq.command.CommandManager;
import cc.moecraft.icq.core.MiraiApi;
import cc.moecraft.icq.core.PicqMiraiEventHandlers;
import cc.moecraft.icq.event.EventManager;
import cc.moecraft.icq.exceptions.BotNotStartedException;
import cc.moecraft.icq.utils.MiscUtils;
import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import cc.moecraft.logger.environments.FileEnv;
import cc.moecraft.logger.format.AnsiColor;
import cc.moecraft.utils.cli.ResourceUtils;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static cc.moecraft.icq.utils.MiscUtils.logInitDone;

public class PicqBotX {
    private Bot miraiBot = null;

    private final LoggerInstanceManager loggerManager = new LoggerInstanceManager();
    private HyLogger logger;

    private MiraiApi miraiApi;
    private EventManager eventManager;
    private CommandManager commandManager;

    public PicqBotX() {
        init();
    }

    /**
     * 如果机器人没有启动（没有使用 {@link #setAccount(long, String)}），则会抛出
     * {@link BotNotStartedException}
     *
     * @return Mirai 机器人
     */
    public @NotNull Bot getMiraiBot() {
        if (miraiBot == null) {
            throw new BotNotStartedException();
        }
        return miraiBot;
    }

    private void init() {
        eventManager = new EventManager(this);
        miraiApi = new MiraiApi(this);

        if (!PicqConfig.getInstance().getLogPath().isEmpty()) {
            loggerManager.addEnvironment(new FileEnv(
                PicqConfig.getInstance().getLogPath(),
                PicqConfig.getInstance().getLogFileName()
            ));
        }

        logger = loggerManager.getLoggerInstance("PicqBotX", PicqConfig.getInstance().isDebug());
        logger.log("初始化...");

        logger.timing.init();

        MiscUtils.logResource(logger, PicqConstants.ASCII_ICON_RES, "version", PicqConstants.PICQ_VERSION);
        logInitDone(logger, "日志管理器     ", 0, 6);

        eventManager = new EventManager(this);
        logInitDone(logger, "事件管理器     ", 3, 3);

        logger.timing.clear();
        logger.log("初始化完成");
    }

    /**
     * 由于目前只支持一个账号，所以此 API 已过时。
     * 需要使用 {@link #setAccount(long, String)}
     *
     * @param qq       qq号
     * @param password 密码
     */
    @Deprecated
    public void addAccount(long qq, @NotNull String password) {
        setAccount(qq, password);
    }

    public void setAccount(long qq, @NotNull String password) {
        setAccount(qq, password, new BotConfiguration() {{
            fileBasedDeviceInfo();
        }});
    }

    public void setAccount(long qq, @NotNull String password, Consumer<BotConfiguration> configBlock) {
        setAccount(qq, password, new BotConfiguration() {{
            configBlock.accept(this);
        }});
    }

    public void setAccount(long qq, @NotNull String password, BotConfiguration botConfiguration) {
        if (miraiBot != null) {
            throw new UnsupportedOperationException("不能设置两次账号！");
        }
        miraiBot = BotFactory.INSTANCE.newBot(qq, password, botConfiguration);
    }

    public void setAccount(long qq, @NotNull byte[] passwordMd5) {
        setAccount(qq, passwordMd5, new BotConfiguration() {{
            fileBasedDeviceInfo();
        }});
    }

    public void setAccount(long qq, @NotNull byte[] passwordMd5, Consumer<BotConfiguration> configBlock) {
        setAccount(qq, passwordMd5, new BotConfiguration() {{
            configBlock.accept(this);
        }});
    }

    public void setAccount(long qq, @NotNull byte[] passwordMd5, BotConfiguration botConfiguration) {
        if (miraiBot != null) {
            throw new UnsupportedOperationException("不能设置两次账号！");
        }
        miraiBot = BotFactory.INSTANCE.newBot(qq, passwordMd5, botConfiguration);
    }

    public void startBot() {
        if (miraiBot == null) {
            throw new BotNotStartedException();
        }
        logger.log(AnsiColor.GREEN + "正在启动..." + AnsiColor.RESET);
        miraiBot.login();
        PicqMessageTemplate.init();
        miraiBot.getEventChannel().registerListenerHost(new PicqMiraiEventHandlers(eventManager));
        logger.log("启动完成");
    }

    /**
     * 初始化指令管理器
     *
     * @param prefixes 前缀
     */
    public void enableCommandManager(String... prefixes)
    {
        logger.timing.init();

        commandManager = new CommandManager(this, prefixes);
        eventManager.setCommandListener(new CommandListener(eventManager, commandManager));
        logInitDone(logger, "指令管理器     ", 6, 0);

        logger.timing.clear();
    }

    public LoggerInstanceManager getLoggerManager() {
        return loggerManager;
    }

    public MiraiApi getMiraiApi() {
        return miraiApi;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
