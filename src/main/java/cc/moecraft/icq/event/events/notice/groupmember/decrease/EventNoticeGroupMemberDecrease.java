package cc.moecraft.icq.event.events.notice.groupmember.decrease;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.events.notice.groupmember.EventNoticeGroupMemberChange;
import net.mamoe.mirai.contact.NormalMember;
import net.mamoe.mirai.event.events.BotLeaveEvent;
import net.mamoe.mirai.event.events.MemberLeaveEvent;

public abstract class EventNoticeGroupMemberDecrease extends EventNoticeGroupMemberChange {
    public EventNoticeGroupMemberDecrease(MemberLeaveEvent miraiEvent, PicqBotX bot, NormalMember member) {
        super(miraiEvent, bot, member);
    }

    public EventNoticeGroupMemberDecrease(BotLeaveEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getGroup().getBotAsMember());
    }
}
