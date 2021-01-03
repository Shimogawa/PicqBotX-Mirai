package cc.moecraft.icq.event.events.notice.groupmember.increase;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.event.events.BotJoinGroupEvent;
import net.mamoe.mirai.event.events.MemberJoinEvent;

public final class EventNoticeGroupMemberInvite extends EventNoticeGroupMemberIncrease {
    public EventNoticeGroupMemberInvite(MemberJoinEvent.Invite miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
    }

    public EventNoticeGroupMemberInvite(BotJoinGroupEvent.Invite miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
    }
}
