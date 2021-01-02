package cc.moecraft.icq.event.events.local;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.MessageChain;

public class EventLocalSendPrivateMessage extends EventLocalSendMessage {
    public EventLocalSendPrivateMessage(PicqBotX bot, User user, MessageChain rawMessage) {
        super(bot, user, rawMessage);
    }
}
