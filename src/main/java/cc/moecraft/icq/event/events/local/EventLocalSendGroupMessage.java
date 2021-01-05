package cc.moecraft.icq.event.events.local;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.MessageChain;

/**
 * 本地调用 API 触发的发送群组消息事件
 */
public class EventLocalSendGroupMessage extends EventLocalSendMessage {
    public EventLocalSendGroupMessage(PicqBotX bot, Group group, MessageChain rawMessage) {
        super(bot, group, rawMessage);
    }
}
