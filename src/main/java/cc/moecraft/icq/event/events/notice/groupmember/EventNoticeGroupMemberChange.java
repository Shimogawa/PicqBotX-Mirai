package cc.moecraft.icq.event.events.notice.groupmember;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.events.notice.EventNotice;
import net.mamoe.mirai.contact.NormalMember;
import net.mamoe.mirai.event.events.BotEvent;

/**
 * 群组成员变动事件
 */
public abstract class EventNoticeGroupMemberChange extends EventNotice {
    /**
     * 发生变动成员
     */
    protected final NormalMember member;

    public EventNoticeGroupMemberChange(BotEvent miraiEvent, PicqBotX bot, NormalMember member) {
        super(miraiEvent, bot, 0, member);
        this.member = member;
    }

    /**
     * 获取发生变动成员
     *
     * @return 发生变动成员
     */
    public NormalMember getMember() {
        return member;
    }

    /**
     * 返回进群/退群的群员是否是 Bot
     *
     * @return 进群/退群的群员是否是 Bot
     */
    public boolean isBotSubject() {
        return member.getId() == miraiEvent.getBot().getId();
    }
}
