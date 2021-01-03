package cc.moecraft.icq.core;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.events.local.EventLocalSendGroupMessage;
import cc.moecraft.icq.event.events.local.EventLocalSendMessage;
import cc.moecraft.icq.event.events.local.EventLocalSendPrivateMessage;
import cc.moecraft.icq.utils.MsgUtils;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class MiraiApi {
    private final PicqBotX bot;

    public MiraiApi(PicqBotX bot) {
        this.bot = bot;
    }

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, String message) {
        return sendPrivateMessage(id, message, true);
    }

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, String message, boolean isTemplate) {
        return sendPrivateMessage(id, MsgUtils.toMessageChain(message, isTemplate));
    }

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, MessageChain message) {
        return sendMessage(bot.getMiraiBot().getFriendOrFail(id), message);
    }

    public @Nullable MessageReceipt<Contact> sendTempMessage(long groupId, long memberId, String message) {
        return sendTempMessage(groupId, memberId, message, true);
    }

    public @Nullable MessageReceipt<Contact> sendTempMessage(
        long groupId,
        long memberId,
        String message,
        boolean isTemplate
    ) {
        return sendTempMessage(groupId, memberId, MsgUtils.toMessageChain(message, isTemplate));
    }

    public @Nullable MessageReceipt<Contact> sendTempMessage(long groupId, long memberId, MessageChain message) {
        return sendMessage(bot.getMiraiBot().getGroupOrFail(groupId).getOrFail(memberId), message);
    }

    public @Nullable MessageReceipt<Contact> sendGroupMessage(long id, String message, boolean isTemplate) {
        return sendGroupMessage(id, MsgUtils.toMessageChain(message, isTemplate));
    }

    public @Nullable MessageReceipt<Contact> sendGroupMessage(long id, MessageChain message) {
        return sendMessage(bot.getMiraiBot().getGroupOrFail(id), message);
    }

    /**
     * 发送消息
     *
     * @param contact 一个用户对象或群对象
     * @param message 消息链
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截。
     */
    public @Nullable MessageReceipt<Contact> sendMessage(@NotNull Contact contact, @Nullable MessageChain message) {
        if (message == null) return null;
        EventLocalSendMessage e = contact instanceof Group
            ? new EventLocalSendGroupMessage(bot, (Group) contact, message)
            : new EventLocalSendPrivateMessage(bot, (User) contact, message);
        bot.getEventManager().call(e);
        if (e.isCancelled()) {
            return null;
        }
        return contact.sendMessage(message);
    }
}
