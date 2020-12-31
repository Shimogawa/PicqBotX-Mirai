package cc.moecraft.icq.event.events.message;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.event.events.FriendMessageEvent;

public class EventPrivateMessage extends EventMessage {
    public EventPrivateMessage(FriendMessageEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
    }
}
