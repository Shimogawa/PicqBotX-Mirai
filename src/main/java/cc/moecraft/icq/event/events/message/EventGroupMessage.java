package cc.moecraft.icq.event.events.message;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public final class EventGroupMessage extends EventMessage {
    private final long groupId;

    private final Group group;

    public EventGroupMessage(GroupMessageEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
        this.group = miraiEvent.getGroup();
        this.groupId = group.getId();
    }

    public long getGroupId() {
        return groupId;
    }

    public Group getGroup() {
        return group;
    }
}
