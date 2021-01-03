package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.Event;
import cc.moecraft.icq.event.events.notice.groupadmin.EventNoticeGroupAdminChange;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.BotEvent;

public abstract class EventNotice extends Event {
    /**
     * 事件发起人
     * <p>
     * 注：
     * 在撤回事件中，指撤回消息的人，而不是消息发送者。
     * 请使用 {@link EventNoticeRecall#getOperator()} 或 {@link EventNoticeRecall#getAuthor()}
     * <p>
     * 在禁言事件中，指发起禁言的管理员，而不是被禁言的人。
     * 请使用 {@link EventNoticeGroupBan#getOperator()} 或 {@link EventNoticeGroupBan#getBannedMember()}。
     * <p>
     * 在管理员变动事件中，指发生变动的成员。
     * 请使用 {@link EventNoticeGroupAdminChange#getMember()}。
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
