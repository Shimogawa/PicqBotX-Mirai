package cc.moecraft.icq.event.events.local;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.MessageChain;

public class EventLocalSendGroupMessage extends EventLocalSendMessage {
    public EventLocalSendGroupMessage(PicqBotX bot, Group group, MessageChain rawMessage) {
        super(bot, group, rawMessage);
    }
}
