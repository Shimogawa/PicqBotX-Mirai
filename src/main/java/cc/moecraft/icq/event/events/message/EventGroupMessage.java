package cc.moecraft.icq.event.events.message;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * 群组消息事件
 */
public final class EventGroupMessage extends EventMessage {
    /**
     * 群号
     */
    private final long groupId;

    /**
     * 群
     */
    private final Group group;

    public EventGroupMessage(GroupMessageEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
        this.group = miraiEvent.getGroup();
        this.groupId = group.getId();
    }

    /**
     * 获取群号
     *
     * @return 群号
     */
    public long getGroupId() {
        return groupId;
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
