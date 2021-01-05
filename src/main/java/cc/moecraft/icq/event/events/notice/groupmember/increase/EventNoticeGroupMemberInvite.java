package cc.moecraft.icq.event.events.notice.groupmember.increase;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.event.events.BotJoinGroupEvent;
import net.mamoe.mirai.event.events.MemberJoinEvent;

/**
 * 群组被邀请加入成员事件
 */
public final class EventNoticeGroupMemberInvite extends EventNoticeGroupMemberIncrease {
    public EventNoticeGroupMemberInvite(MemberJoinEvent.Invite miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
    }

    public EventNoticeGroupMemberInvite(BotJoinGroupEvent.Invite miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
    }
}
