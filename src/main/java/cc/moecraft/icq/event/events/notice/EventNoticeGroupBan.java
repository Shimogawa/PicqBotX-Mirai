package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.NormalMember;
import net.mamoe.mirai.event.events.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class EventNoticeGroupBan extends EventNotice {
    private final BanType type;
    private final long groupId;
    private final Group group;
    private final long operatorId;
    private final Member operator;
    /**
     * （之前或将要）被禁言的成员 QQ 号
     */
    private final long bannedMemberId;
    /**
     * （之前或将要）被禁言的成员
     */
    private final Member bannedMember;
    @Deprecated
    @SuppressWarnings("DeprecatedIsStillUsed")
    private final long duration;
    /**
     * 如果是解除禁言，时长会是 0
     */
    private final long durationSeconds;

    public EventNoticeGroupBan(MemberMuteEvent miraiEvent, PicqBotX bot) {
        this(
            miraiEvent,
            bot,
            BanType.BAN,
            miraiEvent.getGroup(),
            miraiEvent.getOperator() == null
                ? miraiEvent.getGroup().getBotAsMember()
                : Objects.requireNonNull(miraiEvent.getOperator()),
            miraiEvent.getMember(),
            miraiEvent.getDurationSeconds()
        );
    }

    public EventNoticeGroupBan(BotMuteEvent miraiEvent, PicqBotX bot) {
        this(
            miraiEvent,
            bot,
            BanType.BAN,
            miraiEvent.getGroup(),
            miraiEvent.getOperator(),
            miraiEvent.getGroup().getBotAsMember(),
            miraiEvent.getDurationSeconds()
        );
    }

    public EventNoticeGroupBan(MemberUnmuteEvent miraiEvent, PicqBotX bot) {
        this(
            miraiEvent,
            bot,
            BanType.LIFT_BAN,
            miraiEvent.getGroup(),
            miraiEvent.getOperator() == null
                ? miraiEvent.getGroup().getBotAsMember()
                : Objects.requireNonNull(miraiEvent.getOperator()),
            miraiEvent.getMember(),
            0
        );
    }

    public EventNoticeGroupBan(BotUnmuteEvent miraiEvent, PicqBotX bot) {
        this(
            miraiEvent,
            bot,
            BanType.LIFT_BAN,
            miraiEvent.getGroup(),
            miraiEvent.getOperator(),
            miraiEvent.getGroup().getBotAsMember(),
            0
        );
    }

    public EventNoticeGroupBan(
        BotEvent miraiEvent,
        PicqBotX bot,
        BanType type,
        Group group,
        @NotNull Member operator,
        Member bannedMember,
        long durationSeconds
    ) {
        super(miraiEvent, bot, 0, operator);
        this.type = type;
        this.groupId = group.getId();
        this.group = group;
        this.operatorId = operator.getId();
        this.operator = operator;
        this.bannedMemberId = bannedMember.getId();
        this.bannedMember = bannedMember;
        this.duration = durationSeconds;
        this.durationSeconds = durationSeconds;
    }

    public BanType getType() {
        return type;
    }

    public long getGroupId() {
        return groupId;
    }

    public Group getGroup() {
        return group;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public Member getOperator() {
        return operator;
    }

    public long getBannedMemberId() {
        return bannedMemberId;
    }

    public Member getBannedMember() {
        return bannedMember;
    }

    @Deprecated
    public long getDuration() {
        return duration;
    }

    public long getDurationSeconds() {
        return durationSeconds;
    }

    /**
     * 解除禁言
     *
     * @throws net.mamoe.mirai.contact.PermissionDeniedException 无权限修改时抛出
     */
    public void unmute() {
        if (bannedMember instanceof NormalMember) {
            ((NormalMember) bannedMember).unmute();
        }
    }

    /**
     * 禁言
     *
     * @param length 时长（秒），可以是 0 秒 ~ 30 天
     * @throws IllegalStateException                             时长超过范围时抛出
     * @throws net.mamoe.mirai.contact.PermissionDeniedException 无权限修改时抛出
     */
    public void mute(int length) {
        bannedMember.mute(length);
    }

    public enum BanType {
        BAN,
        LIFT_BAN
    }
}
