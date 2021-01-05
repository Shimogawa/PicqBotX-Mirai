package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.event.events.BotNudgedEvent;
import net.mamoe.mirai.event.events.FriendNudgedEvent;

/**
 * 好友戳一戳事件
 */
public final class EventNoticeFriendPoke extends EventNotice {
    /**
     * 被戳的人的 QQ 号
     */
    private final long targetId;

    /**
     * 被戳的人（可以是机器人）
     */
    private final Friend target;

    public EventNoticeFriendPoke(FriendNudgedEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getFrom());
        this.targetId = miraiEvent.getFrom().getId();
        this.target = miraiEvent.getFrom();
    }

    public EventNoticeFriendPoke(BotNudgedEvent.InPrivateSession miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getFrom());
        this.target = miraiEvent.getBot().getAsFriend();
        this.targetId = target.getId();
    }

    /**
     * 获取被戳的人的 QQ 号
     * @return 被戳的人的 QQ 号
     */
    public long getTargetId() {
        return targetId;
    }

    /**
     * 被戳的人（可以是机器人）
     * @return 被戳的人（可以是机器人）
     */
    public Friend getTarget() {
        return target;
    }
}
