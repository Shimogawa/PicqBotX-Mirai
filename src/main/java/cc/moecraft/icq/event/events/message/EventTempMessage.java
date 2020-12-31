package cc.moecraft.icq.event.events.message;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.TempMessageEvent;

public final class EventTempMessage extends EventMessage {
    private final Group group;

    public EventTempMessage(TempMessageEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
        this.group = miraiEvent.getGroup();
    }

    public Group getGroup() {
        return group;
    }
}
