package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.MessageRecallEvent;

public final class EventNoticeGroupRecall extends EventNoticeRecall {
    private final Group group;

    public EventNoticeGroupRecall(MessageRecallEvent.GroupRecall miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getOperator());
        this.group = miraiEvent.getGroup();
    }

    public Group getGroup() {
        return group;
    }
}
