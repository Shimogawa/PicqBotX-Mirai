package cc.moecraft.icq.event.events.message;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.event.events.FriendMessageEvent;

/**
 * 私聊消息事件
 */
public class EventPrivateMessage extends EventMessage {
    public EventPrivateMessage(FriendMessageEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot);
    }
}
