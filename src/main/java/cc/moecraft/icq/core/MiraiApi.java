package cc.moecraft.icq.core;

import cc.moecraft.icq.PicqBotX;

public class MiraiApi {
    private final PicqBotX bot;

    public MiraiApi(PicqBotX bot) {
        this.bot = bot;
    }

    public void sendPrivateMessage(long id, String message) {
        bot.getMiraiBot().getFriendOrFail(id).sendMessage(message);
    }

}
