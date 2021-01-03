package cc.moecraft.icq.event.events.notice.groupmember.decrease;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.event.events.BotLeaveEvent;
import net.mamoe.mirai.event.events.MemberLeaveEvent;

public final class EventNoticeGroupMemberLeave extends EventNoticeGroupMemberDecrease {
    public EventNoticeGroupMemberLeave(MemberLeaveEvent.Quit miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getMember());
    }

    public EventNoticeGroupMemberLeave(BotLeaveEvent.Active miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
    }
}
