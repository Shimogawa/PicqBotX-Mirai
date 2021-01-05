package cc.moecraft.icq.event.events.message;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.TempMessageEvent;

/**
 * 临时会话消息事件
 */
public final class EventTempMessage extends EventMessage {
    /**
     * 群
     */
    private final Group group;

    public EventTempMessage(TempMessageEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
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
