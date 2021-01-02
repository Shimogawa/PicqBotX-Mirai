package cc.moecraft.icq.event.events.local;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.MessageChain;

public abstract class EventLocalSendMessage extends EventLocal {
    /**
     * 群号 或 好友 QQ 号 或 群员 QQ 号
     */
    protected final long id;

    protected final Contact contact;

    protected final MessageChain rawMessage;

    public EventLocalSendMessage(PicqBotX bot, Contact contact, MessageChain rawMessage) {
        super(null, bot);
        this.id = contact.getId();
        this.contact = contact;
        this.rawMessage = rawMessage;
    }

    public long getId() {
        return id;
    }

    public Contact getContact() {
        return contact;
    }

    public MessageChain getRawMessage() {
        return rawMessage;
    }
}
