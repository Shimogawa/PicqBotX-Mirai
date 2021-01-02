package cc.moecraft.icq.event.events.message;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqMessageTemplate;
import cc.moecraft.icq.event.Event;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageSource;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;
import org.jetbrains.annotations.Nullable;

public abstract class EventMessage extends Event {
    /**
     * Message Template
     */
    protected final String message;

    protected final MessageChain rawMessage;

    protected final long senderId;

    protected final User sender;

    protected final int eventTime;

    public EventMessage(MessageEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getTime());
        this.message = PicqMessageTemplate.toMessageTemplate(miraiEvent.getMessage());
        this.rawMessage = miraiEvent.getMessage();
        this.sender = miraiEvent.getSender();
        this.senderId = sender.getId();
        this.eventTime = miraiEvent.getTime();
    }

    public String getMessage() {
        return message;
    }

    public MessageChain getRawMessage() {
        return rawMessage;
    }

    public long getSenderId() {
        return senderId;
    }

    public User getSender() {
        return sender;
    }

    public int getEventTime() {
        return eventTime;
    }

    public MessageReceipt<Contact> respond(@Nullable String response) {
        return respond(response, false, false);
    }

    public MessageReceipt<Contact> respond(@Nullable String response, boolean isMessageTemplate) {
        return respond(response, isMessageTemplate, false);
    }

    public MessageReceipt<Contact> respond(@Nullable String response, boolean isMessageTemplate, boolean quote) {
        if (response == null) return null;
        return respond(
            isMessageTemplate
                ? MessageUtils.newChain(PicqMessageTemplate.messageTemplateToList(response))
                : MessageUtils.newChain(new PlainText(response)),
            quote
        );
    }

    public @Nullable MessageReceipt<Contact> respond(@Nullable MessageChain response) {
        return respond(response, false);
    }

    @SuppressWarnings("unchecked")
    public @Nullable MessageReceipt<Contact> respond(@Nullable MessageChain response, boolean quote) {
        if (response == null) return null;
        return ((MessageEvent) miraiEvent).getSubject().sendMessage(
            quote
                ? MessageSource.quote(rawMessage).plus(response)
                : response
        );
    }

    @SuppressWarnings("unchecked")
    public @Nullable MessageReceipt<Contact> respondPrivateMessage(@Nullable String response, boolean isMessageTemplate) {
        if (response == null) return null;
        return ((MessageEvent) miraiEvent).getSender().sendMessage(
            isMessageTemplate
                ? MessageUtils.newChain(PicqMessageTemplate.messageTemplateToList(response))
                : new PlainText(response)
        );
    }

    @SuppressWarnings("unchecked")
    public @Nullable MessageReceipt<Contact> respondPrivateMessage(@Nullable MessageChain response) {
        if (response == null) return null;
        return ((MessageEvent) miraiEvent).getSender().sendMessage(response);
    }
}
