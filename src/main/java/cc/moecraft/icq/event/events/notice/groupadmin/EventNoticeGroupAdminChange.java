package cc.moecraft.icq.event.events.notice.groupadmin;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.events.notice.EventNotice;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.event.events.BotGroupPermissionChangeEvent;
import net.mamoe.mirai.event.events.MemberPermissionChangeEvent;

public final class EventNoticeGroupAdminChange extends EventNotice {
    private final Member member;

    private final MemberPermission oldPermission;

    private final MemberPermission newPermission;

    public EventNoticeGroupAdminChange(MemberPermissionChangeEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getMember());
        this.member = miraiEvent.getMember();
        this.oldPermission = miraiEvent.getOrigin();
        this.newPermission = miraiEvent.getNew();
    }

    public EventNoticeGroupAdminChange(BotGroupPermissionChangeEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getGroup().getBotAsMember());
        this.member = miraiEvent.getGroup().getBotAsMember();
        this.oldPermission = miraiEvent.getOrigin();
        this.newPermission = miraiEvent.getNew();
    }

    public Member getMember() {
        return member;
    }

    public MemberPermission getOldPermission() {
        return oldPermission;
    }

    public MemberPermission getNewPermission() {
        return newPermission;
    }

    public boolean isSetAdmin() {
        return oldPermission.compareTo(newPermission) < 0;
    }

    public boolean isRemoveAdmin() {
        return oldPermission.compareTo(newPermission) > 0;
    }
}
