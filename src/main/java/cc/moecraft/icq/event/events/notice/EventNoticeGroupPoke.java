package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.events.BotNudgedEvent;
import net.mamoe.mirai.event.events.MemberNudgedEvent;

/**
 * 群组戳一戳事件
 */
public final class EventNoticeGroupPoke extends EventNotice {
    /**
     * 被戳的人的 QQ 号
     */
    private final long targetId;

    /**
     * 被戳的人
     */
    private final Member target;

    /**
     * 发起戳一戳的人的 QQ 号
     */
    private final long fromId;

    /**
     * 发起戳一戳的人
     */
    private final Member from;

    /**
     * 群
     */
    private final Group group;

    /**
     * 群号
     */
    private final long groupId;

    public EventNoticeGroupPoke(MemberNudgedEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getFrom());
        this.target = miraiEvent.getMember();
        this.targetId = target.getId();
        this.from = miraiEvent.getFrom();
        this.fromId = from.getId();
        this.group = miraiEvent.getGroup();
        this.groupId = group.getId();
    }

    public EventNoticeGroupPoke(BotNudgedEvent.InGroup miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getFrom());
        this.target = miraiEvent.getGroup().getBotAsMember();
        this.targetId = target.getId();
        this.from = miraiEvent.getFrom();
        this.fromId = from.getId();
        this.group = miraiEvent.getGroup();
        this.groupId = group.getId();
    }

    /**
     * 获取被戳的人的 QQ 号
     *
     * @return 被戳的人的 QQ 号
     */
    public long getTargetId() {
        return targetId;
    }

    /**
     * 获取被戳的人
     *
     * @return 被戳的人
     */
    public Member getTarget() {
        return target;
    }

    /**
     * 获取发起戳一戳的人的 QQ 号
     *
     * @return 发起戳一戳的人的 QQ 号
     */
    public long getFromId() {
        return fromId;
    }

    /**
     * 获取发起戳一戳的人
     *
     * @return 发起戳一戳的人
     */
    public Member getFrom() {
        return from;
    }

    /**
     * 获取群
     *
     * @return 群
     */
    public Group getGroup() {
        return group;
    }

    /**
     * 获取群号
     *
     * @return 群号
     */
    public long getGroupId() {
        return groupId;
    }
}
