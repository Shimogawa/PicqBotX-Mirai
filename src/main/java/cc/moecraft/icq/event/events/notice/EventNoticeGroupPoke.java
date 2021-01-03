package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.events.BotNudgedEvent;
import net.mamoe.mirai.event.events.MemberNudgedEvent;

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

    public long getTargetId() {
        return targetId;
    }

    public Member getTarget() {
        return target;
    }

    public long getFromId() {
        return fromId;
    }

    public Member getFrom() {
        return from;
    }

    public Group getGroup() {
        return group;
    }

    public long getGroupId() {
        return groupId;
    }
}
