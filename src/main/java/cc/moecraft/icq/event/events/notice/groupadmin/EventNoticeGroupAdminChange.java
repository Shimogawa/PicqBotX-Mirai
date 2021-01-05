package cc.moecraft.icq.event.events.notice.groupadmin;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.events.notice.EventNotice;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.MemberPermission;
import net.mamoe.mirai.event.events.BotGroupPermissionChangeEvent;
import net.mamoe.mirai.event.events.MemberPermissionChangeEvent;

/**
 * 群组管理员变动事件
 */
public final class EventNoticeGroupAdminChange extends EventNotice {
    /**
     * 发生变动的成员
     */
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

    /**
     * 获取发生变动的成员
     *
     * @return 发生变动的成员
     */
    public Member getMember() {
        return member;
    }

    /**
     * 获取原先的权限
     *
     * @return 原先的权限
     */
    public MemberPermission getOldPermission() {
        return oldPermission;
    }

    /**
     * 获取现在的权限
     *
     * @return 现在的权限
     */
    public MemberPermission getNewPermission() {
        return newPermission;
    }

    /**
     * 返回是否是设置新管理
     *
     * @return 是否是设置新管理
     */
    public boolean isSetAdmin() {
        return oldPermission.compareTo(newPermission) < 0;
    }

    /**
     * 返回是否是移除管理
     *
     * @return 是否是移除管理
     */
    public boolean isRemoveAdmin() {
        return oldPermission.compareTo(newPermission) > 0;
    }
}
