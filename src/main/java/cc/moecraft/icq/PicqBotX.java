package cc.moecraft.icq;

import cc.moecraft.icq.core.PicqMiraiEventHandlers;
import cc.moecraft.icq.event.EventManager;
import cc.moecraft.icq.exceptions.BotNotStartedException;
import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;

public class PicqBotX {
    private Bot miraiBot = null;

    private final LoggerInstanceManager loggerManager = new LoggerInstanceManager();
    private HyLogger logger;
    private EventManager eventManager;

    public PicqBotX() {
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
        logger = loggerManager.getLoggerInstance("PicqBotX", PicqConfig.getInstance().isDebug());
        eventManager = new EventManager(this);
    }

    /**
     * Originally `addAccount`.
     */
    public void setAccount(long qq, @NotNull String password) {
        if (miraiBot != null) {
            throw new UnsupportedOperationException("不能设置两次账号！");
        }
        miraiBot = BotFactory.INSTANCE.newBot(qq, password, new BotConfiguration() {{
            fileBasedDeviceInfo();
        }});
    }

    public void startBot() {
        if (miraiBot == null) {
            throw new BotNotStartedException();
        }
        miraiBot.login();
        PicqMessageTemplate.init();
        miraiBot.getEventChannel().registerListenerHost(new PicqMiraiEventHandlers(eventManager));
    }
}
