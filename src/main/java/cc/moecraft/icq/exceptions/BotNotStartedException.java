package cc.moecraft.icq.exceptions;

/**
 * 机器人还没有启动的异常
 */
public class BotNotStartedException extends PicqException {
    public BotNotStartedException() {
        super("Bot not started.");
    }

    public BotNotStartedException(String reason) {
        super(reason);
    }
}
