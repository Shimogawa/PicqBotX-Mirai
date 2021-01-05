package cc.moecraft.icq.event.events.local;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.MessageChain;

/**
 * 本地调用 API 触发的发送消息事件
 */
public abstract class EventLocalSendMessage extends EventLocal {
    /**
     * 群号 或 好友 QQ 号 或 群员 QQ 号
     */
    protected final long id;

    /**
     * 群 或 好友 或 群员
     */
    protected final Contact contact;

    /**
     * 准备发送的消息
     */
    protected final MessageChain rawMessage;

    public EventLocalSendMessage(PicqBotX bot, Contact contact, MessageChain rawMessage) {
        super(null, bot);
        this.id = contact.getId();
        this.contact = contact;
        this.rawMessage = rawMessage;
    }

    /**
     * 获取 群号 或 好友 QQ 号 或 群员 QQ 号
     *
     * @return 群号 或 好友 QQ 号 或 群员 QQ 号
     */
    public long getId() {
        return id;
    }

    /**
     * 获取群 或 好友 或 群员
     *
     * @return 群 或 好友 或 群员
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * 获取准备发送的消息
     *
     * @return 准备发送的消息
     */
    public MessageChain getRawMessage() {
        return rawMessage;
    }
}
