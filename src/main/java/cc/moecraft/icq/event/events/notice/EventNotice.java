package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.Event;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.BotEvent;

public class EventNotice extends Event {
    /**
     * 事件发起人
     *
     * 注：在撤回事件中，指撤回消息的人，而不是消息发送者。
     * 请使用 {@link EventNoticeRecall#getOperator()} 或 {@link EventNoticeRecall#getAuthor()}
     */
    protected final User user;

    /**
     * 事件发起人 QQ 号
     */
    protected final long userId;

    public EventNotice(BotEvent miraiEvent, PicqBotX bot, int time, User user) {
        super(miraiEvent, bot, time);
        this.user = user;
        this.userId = user.getId();
    }

    public User getUser() {
        return user;
    }

    public long getUserId() {
        return userId;
    }
}