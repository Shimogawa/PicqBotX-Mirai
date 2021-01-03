package cc.moecraft.icq.event.events.notice.groupmember;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.events.notice.EventNotice;
import net.mamoe.mirai.contact.NormalMember;
import net.mamoe.mirai.event.events.BotEvent;

public abstract class EventNoticeGroupMemberChange extends EventNotice {
    protected final NormalMember member;

    public EventNoticeGroupMemberChange(BotEvent miraiEvent, PicqBotX bot, NormalMember member) {
        super(miraiEvent, bot, 0, member);
        this.member = member;
    }

    public NormalMember getMember() {
        return member;
    }

    /**
     * @return 进群/退群的群员是否是 Bot
     */
    public boolean isBotSubject() {
        return member.getId() == miraiEvent.getBot().getId();
    }
}
