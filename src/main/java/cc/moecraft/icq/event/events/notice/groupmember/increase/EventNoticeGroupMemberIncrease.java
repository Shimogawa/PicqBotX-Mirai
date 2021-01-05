package cc.moecraft.icq.event.events.notice.groupmember.increase;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.events.notice.groupmember.EventNoticeGroupMemberChange;
import net.mamoe.mirai.event.events.BotJoinGroupEvent;
import net.mamoe.mirai.event.events.MemberJoinEvent;

/**
 * 群组成员增加事件
 */
public abstract class EventNoticeGroupMemberIncrease extends EventNoticeGroupMemberChange {
    public EventNoticeGroupMemberIncrease(MemberJoinEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getMember());
    }

    public EventNoticeGroupMemberIncrease(BotJoinGroupEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getGroup().getBotAsMember());
    }

    /**
     * 踢出加入的成员
     *
     * @param message 消息（不是消息模板）
     */
    public void kick(String message) {
        if (getUserId() == miraiEvent.getBot().getId()) return;
        member.kick(message);
    }
}
