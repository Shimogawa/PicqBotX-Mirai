package cc.moecraft.icq.exceptions;

public class BotNotStartedException extends PicqException {
    public BotNotStartedException() {
        super("Bot not started.");
    }

    public BotNotStartedException(String reason) {
        super(reason);
    }
}
