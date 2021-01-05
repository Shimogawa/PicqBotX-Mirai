package cc.moecraft.icq.event.events.message;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.PicqMessageTemplate;
import cc.moecraft.icq.event.Event;
import cc.moecraft.icq.utils.MsgUtils;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageSource;
import org.jetbrains.annotations.Nullable;

/**
 * 消息事件
 */
public abstract class EventMessage extends Event {
    /**
     * 消息（默认是消息模板）
     *
     * @see PicqMessageTemplate
     */
    protected final String message;

    /**
     * 原消息（消息链）
     */
    protected final MessageChain rawMessage;

    /**
     * 发送者 QQ 号
     */
    protected final long senderId;

    /**
     * 发送者（好友或群员）
     */
    protected final User sender;

    /**
     * 事件时间
     *
     * @see Event#time
     */
    protected final int eventTime;

    public EventMessage(MessageEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getTime());
        this.message = PicqMessageTemplate.toMessageTemplate(miraiEvent.getMessage());
        this.rawMessage = miraiEvent.getMessage();
        this.sender = miraiEvent.getSender();
        this.senderId = sender.getId();
        this.eventTime = miraiEvent.getTime();
    }

    /**
     * 获取消息（默认是消息模板）
     *
     * @return 消息（默认是消息模板）
     * @see PicqMessageTemplate
     */
    public String getMessage() {
        return message;
    }

    /**
     * 获取原消息（消息链）
     *
     * @return 原消息
     */
    public MessageChain getRawMessage() {
        return rawMessage;
    }

    /**
     * 获取发送者 QQ 号
     *
     * @return 发送者 QQ 号
     */
    public long getSenderId() {
        return senderId;
    }

    /**
     * 获取发送者（好友或群员）
     *
     * @return 发送者（好友或群员）
     * @see net.mamoe.mirai.contact.Member
     * @see net.mamoe.mirai.contact.Friend
     */
    public User getSender() {
        return sender;
    }

    /**
     * 获取事件时间
     *
     * @return 事件时间
     * @see Event#getTime()
     */
    public int getEventTime() {
        return eventTime;
    }

    /**
     * 不引用地回复一条消息
     *
     * @param response 回复（默认消息模板）
     * @return 回执
     */
    public @Nullable MessageReceipt<Contact> respond(@Nullable String response) {
        return respond(response, PicqConfig.getInstance().isDefaultSendUseMessageTemplate(), false);
    }

    /**
     * 不引用地回复一条消息
     *
     * @param response          回复
     * @param isMessageTemplate 是否是消息模板
     * @return 回执
     */
    public @Nullable MessageReceipt<Contact> respond(@Nullable String response, boolean isMessageTemplate) {
        return respond(response, isMessageTemplate, false);
    }

    /**
     * 回复一条消息
     *
     * @param response          回复
     * @param isMessageTemplate 是否是消息模板
     * @param quote             是否引用
     * @return 回执
     */
    public @Nullable MessageReceipt<Contact> respond(
        @Nullable String response,
        boolean isMessageTemplate,
        boolean quote
    ) {
        if (response == null) return null;
        return respond(MsgUtils.toMessageChain(response, isMessageTemplate), quote);
    }

    /**
     * 不引用地回复一条消息
     *
     * @param response 回复
     * @return 回执
     */
    public @Nullable MessageReceipt<Contact> respond(@Nullable MessageChain response) {
        return respond(response, false);
    }

    /**
     * 回复一条消息
     *
     * @param response 回复
     * @param quote    是否引用
     * @return 回执
     */
    public @Nullable MessageReceipt<Contact> respond(@Nullable MessageChain response, boolean quote) {
        if (response == null) return null;
        MessageChain message = quote
            ? MessageSource.quote(rawMessage).plus(response)
            : response;
        if (this instanceof EventGroupMessage) {
            return bot.getMiraiApi().sendMessage(((EventGroupMessage) this).getGroup(), message);
        }
        return bot.getMiraiApi().sendMessage(getSender(), message);
    }

    /**
     * 私聊回复一条消息
     *
     * @param response 回复（默认消息模板）
     * @return 回执
     */
    public @Nullable MessageReceipt<Contact> respondPrivateMessage(@Nullable String response) {
        return respondPrivateMessage(response, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    /**
     * 私聊回复一条消息
     *
     * @param response          回复
     * @param isMessageTemplate 是否消息模板
     * @return 回执
     */
    public @Nullable MessageReceipt<Contact> respondPrivateMessage(
        @Nullable String response,
        boolean isMessageTemplate
    ) {
        if (response == null) return null;
        return bot.getMiraiApi().sendMessage(getSender(), MsgUtils.toMessageChain(response, isMessageTemplate));
    }

    /**
     * 私聊回复一条消息
     *
     * @param response 回复
     * @return 回执
     */
    public @Nullable MessageReceipt<Contact> respondPrivateMessage(@Nullable MessageChain response) {
        if (response == null) return null;
        return bot.getMiraiApi().sendMessage(getSender(), response);
    }
}
