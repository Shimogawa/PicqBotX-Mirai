package cc.moecraft.icq.event.events.notice.groupmember.increase;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.event.events.BotJoinGroupEvent;
import net.mamoe.mirai.event.events.MemberJoinEvent;

/**
 * 群组管理员同意加群请求事件
 */
public final class EventNoticeGroupMemberApprove extends EventNoticeGroupMemberIncrease {
    public EventNoticeGroupMemberApprove(MemberJoinEvent.Active miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
    }

    public EventNoticeGroupMemberApprove(BotJoinGroupEvent.Active miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
    }
}
