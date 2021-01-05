package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.MessageRecallEvent;

/**
 * 群组撤回消息事件
 */
public final class EventNoticeGroupRecall extends EventNoticeRecall {
    /**
     * 群
     */
    private final Group group;

    public EventNoticeGroupRecall(MessageRecallEvent.GroupRecall miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getOperator());
        this.group = miraiEvent.getGroup();
    }

    /**
     * 获取群
     *
     * @return 群
     */
    public Group getGroup() {
        return group;
    }
}
