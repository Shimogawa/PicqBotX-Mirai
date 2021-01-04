package cc.moecraft.icq.core;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.event.events.local.EventLocalSendGroupMessage;
import cc.moecraft.icq.event.events.local.EventLocalSendMessage;
import cc.moecraft.icq.event.events.local.EventLocalSendPrivateMessage;
import cc.moecraft.icq.utils.MsgUtils;
import net.mamoe.mirai.contact.*;
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

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, @Nullable String message) {
        return sendPrivateMessage(id, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, @Nullable String message, boolean isTemplate) {
        return sendPrivateMessage(id, MsgUtils.toMessageChain(message, isTemplate));
    }

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, @Nullable MessageChain message) {
        return sendPrivateMessage(bot.getMiraiBot().getFriendOrFail(id), message);
    }

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(@NotNull Friend friend, @Nullable String message) {
        return sendPrivateMessage(friend, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(@NotNull Friend friend, @Nullable String message, boolean isTemplate) {
        return sendPrivateMessage(friend, MsgUtils.toMessageChain(message, isTemplate));
    }

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(@NotNull Friend friend, @Nullable MessageChain message) {
        return sendMessage(friend, message);
    }

    public @Nullable MessageReceipt<Contact> sendTempMessage(long groupId, long memberId, @Nullable String message) {
        return sendTempMessage(groupId, memberId, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    public @Nullable MessageReceipt<Contact> sendTempMessage(
        long groupId,
        long memberId,
        @Nullable String message,
        boolean isTemplate
    ) {
        return sendTempMessage(groupId, memberId, MsgUtils.toMessageChain(message, isTemplate));
    }

    public @Nullable MessageReceipt<Contact> sendTempMessage(long groupId, long memberId, @Nullable MessageChain message) {
        return sendTempMessage(bot.getMiraiBot().getGroupOrFail(groupId).getOrFail(memberId), message);
    }

    public @Nullable MessageReceipt<Contact> sendTempMessage(@NotNull Member member, @Nullable String message) {
        return sendTempMessage(member, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    public @Nullable MessageReceipt<Contact> sendTempMessage(@NotNull Member member, @Nullable String message, boolean isTemplate) {
        return sendTempMessage(member, MsgUtils.toMessageChain(message, isTemplate));
    }

    public @Nullable MessageReceipt<Contact> sendTempMessage(@NotNull Member member, @Nullable MessageChain message) {
        return sendMessage(member, message);
    }

    public @Nullable MessageReceipt<Contact> sendGroupMessage(long id, @Nullable String message) {
        return sendGroupMessage(id, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    public @Nullable MessageReceipt<Contact> sendGroupMessage(long id, @Nullable String message, boolean isTemplate) {
        return sendGroupMessage(id, MsgUtils.toMessageChain(message, isTemplate));
    }

    public @Nullable MessageReceipt<Contact> sendGroupMessage(long id, @Nullable MessageChain message) {
        return sendGroupMessage(bot.getMiraiBot().getGroupOrFail(id), message);
    }

    public @Nullable MessageReceipt<Contact> sendGroupMessage(@NotNull Group group, @Nullable String message) {
        return sendGroupMessage(group, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    public @Nullable MessageReceipt<Contact> sendGroupMessage(@NotNull Group group, @Nullable String message, boolean isTemplate) {
        return sendGroupMessage(group, MsgUtils.toMessageChain(message, isTemplate));
    }

    public @Nullable MessageReceipt<Contact> sendGroupMessage(@NotNull Group group, @Nullable MessageChain message) {
        return sendMessage(group, message);
    }

    /**
     * 发送消息
     *
     * @param contact 一个用户对象或群对象
     * @param message 消息链
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
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
