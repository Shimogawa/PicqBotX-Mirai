package cc.moecraft.icq.event.events.request;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.Event;
import net.mamoe.mirai.event.events.BotEvent;

public abstract class EventRequest extends Event {
    /**
     * 收到来自这个 QQ 号的请求
     */
    protected final long userId;

    protected final String userNickname;

    protected final String comment;

    public EventRequest(BotEvent miraiEvent, PicqBotX bot, long userId, String userNickname, String comment) {
        super(miraiEvent, bot, 0);
        this.userId = userId;
        this.userNickname = userNickname;
        this.comment = comment;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public String getComment() {
        return comment;
    }

    public abstract void accept();

    public abstract void reject();
}
