package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.event.events.MessageRecallEvent;

/**
 * 好友撤回消息事件
 */
public final class EventNoticeFriendRecall extends EventNoticeRecall {
    public EventNoticeFriendRecall(MessageRecallEvent.FriendRecall miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getOperator());
    }
}
