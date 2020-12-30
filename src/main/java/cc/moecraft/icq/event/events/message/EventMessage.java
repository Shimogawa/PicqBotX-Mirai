package cc.moecraft.icq.event.events.message;

import cc.moecraft.icq.event.Event;
import net.mamoe.mirai.message.data.MessageChain;

public class EventMessage extends Event {
    protected String messageType;

    public String message;

    protected Long messageId;

    protected MessageChain rawMessage;

    protected Long senderId;
}
