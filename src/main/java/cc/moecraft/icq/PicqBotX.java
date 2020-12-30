package cc.moecraft.icq;

import cc.moecraft.icq.exceptions.BotNotStartedException;
import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PicqBotX {
    private static @Nullable Bot miraiBot = null;
    private final @NotNull PicqConfig config;

    private final LoggerInstanceManager loggerManager = new LoggerInstanceManager();
    private HyLogger logger;

    public PicqBotX() {
        this(PicqConfig.getDefault());
    }

    public PicqBotX(PicqConfig config) {
        this.config = config;
    }

    static @Nullable Bot getMiraiBot() {
        return miraiBot;
    }

    private void init() {
        logger = loggerManager.getLoggerInstance("PicqBotX", config.isDebug());
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
        PicqMessageTemplate.init(config.getScheduledClearWeakRefTimeInterval());
    }

    public PicqConfig getConfig() {
        return config;
    }
}
