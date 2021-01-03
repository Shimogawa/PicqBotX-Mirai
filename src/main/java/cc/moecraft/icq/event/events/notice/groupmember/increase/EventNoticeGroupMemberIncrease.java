package cc.moecraft.icq.event.events.notice.groupmember.increase;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.events.notice.groupmember.EventNoticeGroupMemberChange;
import net.mamoe.mirai.event.events.BotJoinGroupEvent;
import net.mamoe.mirai.event.events.MemberJoinEvent;

public abstract class EventNoticeGroupMemberIncrease extends EventNoticeGroupMemberChange {
    public EventNoticeGroupMemberIncrease(MemberJoinEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getMember());
    }

    public EventNoticeGroupMemberIncrease(BotJoinGroupEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getGroup().getBotAsMember());
    }

    public void kick(String message) {
        if (getUserId() == miraiEvent.getBot().getId()) return;
        member.kick(message);
    }
}
