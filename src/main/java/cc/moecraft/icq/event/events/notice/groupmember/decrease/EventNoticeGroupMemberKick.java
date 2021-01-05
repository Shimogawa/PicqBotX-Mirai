package cc.moecraft.icq.event.events.notice.groupmember.decrease;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.events.BotLeaveEvent;
import net.mamoe.mirai.event.events.MemberLeaveEvent;

/**
 * 群组成员被踢事件
 */
public final class EventNoticeGroupMemberKick extends EventNoticeGroupMemberDecrease {
    /**
     * 操作人
     */
    private final Member operator;

    public EventNoticeGroupMemberKick(MemberLeaveEvent.Kick miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getMember());
        this.operator = miraiEvent.getOperator();
    }

    public EventNoticeGroupMemberKick(BotLeaveEvent.Kick miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
        this.operator = miraiEvent.getOperator();
    }

    /**
     * 获取操作人
     *
     * @return 操作人
     */
    public Member getOperator() {
        return operator;
    }
}
