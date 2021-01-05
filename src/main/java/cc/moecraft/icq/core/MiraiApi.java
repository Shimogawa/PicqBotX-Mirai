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

/**
 * Mirai API，负责收发消息等。
 * <p>
 * Mirai 的 {@link Contact} 类提供了发送消息的接口，但是建议尽量使用这里的接口，
 * 因为可以触发事件，并且支持消息模板。
 */
@SuppressWarnings("unchecked")
public class MiraiApi {
    private final PicqBotX bot;

    public MiraiApi(PicqBotX bot) {
        this.bot = bot;
    }

    /**
     * 发送私聊消息
     *
     * @param id      QQ 号
     * @param message 消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, @Nullable String message) {
        return sendPrivateMessage(id, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    /**
     * 发送私聊消息
     *
     * @param id         QQ 号
     * @param message    消息
     * @param isTemplate 是否消息模板
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, @Nullable String message, boolean isTemplate) {
        return sendPrivateMessage(id, MsgUtils.toMessageChain(message, isTemplate));
    }

    /**
     * 发送私聊消息
     *
     * @param id      QQ 号
     * @param message 消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, @Nullable MessageChain message) {
        return sendPrivateMessage(bot.getMiraiBot().getFriendOrFail(id), message);
    }

    /**
     * 发送私聊消息
     *
     * @param friend  好友对象
     * @param message 消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendPrivateMessage(@NotNull Friend friend, @Nullable String message) {
        return sendPrivateMessage(friend, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    /**
     * 发送私聊消息
     *
     * @param friend     好友对象
     * @param message    消息
     * @param isTemplate 是否是消息模板
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendPrivateMessage(
        @NotNull Friend friend,
        @Nullable String message,
        boolean isTemplate
    ) {
        return sendPrivateMessage(friend, MsgUtils.toMessageChain(message, isTemplate));
    }

    /**
     * 发送私聊消息
     *
     * @param friend  好友对象
     * @param message 消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendPrivateMessage(
        @NotNull Friend friend,
        @Nullable MessageChain message
    ) {
        return sendMessage(friend, message);
    }

    /**
     * 发送临时消息给群员
     *
     * @param groupId  群号
     * @param memberId 群员 QQ 号
     * @param message  消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendTempMessage(long groupId, long memberId, @Nullable String message) {
        return sendTempMessage(groupId, memberId, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    /**
     * 发送临时消息给群员
     *
     * @param groupId    群号
     * @param memberId   群员 QQ 号
     * @param message    消息
     * @param isTemplate 是否是消息模板
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendTempMessage(
        long groupId,
        long memberId,
        @Nullable String message,
        boolean isTemplate
    ) {
        return sendTempMessage(groupId, memberId, MsgUtils.toMessageChain(message, isTemplate));
    }

    /**
     * 发送临时消息给群员
     *
     * @param groupId  群号
     * @param memberId 群员 QQ 号
     * @param message  消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendTempMessage(
        long groupId,
        long memberId,
        @Nullable MessageChain message
    ) {
        return sendTempMessage(bot.getMiraiBot().getGroupOrFail(groupId).getOrFail(memberId), message);
    }

    /**
     * 发送临时消息给群员
     *
     * @param member  群员对象
     * @param message 消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendTempMessage(@NotNull NormalMember member, @Nullable String message) {
        return sendTempMessage(member, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    /**
     * 发送临时消息给群员
     *
     * @param member     群员对象
     * @param message    消息
     * @param isTemplate 是否消息模板
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendTempMessage(
        @NotNull NormalMember member,
        @Nullable String message,
        boolean isTemplate
    ) {
        return sendTempMessage(member, MsgUtils.toMessageChain(message, isTemplate));
    }

    /**
     * 发送临时消息给群员
     *
     * @param member  群员对象
     * @param message 消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendTempMessage(
        @NotNull NormalMember member,
        @Nullable MessageChain message
    ) {
        return sendMessage(member, message);
    }

    /**
     * 发送群组消息
     *
     * @param id      群号
     * @param message 消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendGroupMessage(long id, @Nullable String message) {
        return sendGroupMessage(id, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    /**
     * 发送群组消息
     *
     * @param id         群号
     * @param message    消息
     * @param isTemplate 是否是消息模板
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendGroupMessage(long id, @Nullable String message, boolean isTemplate) {
        return sendGroupMessage(id, MsgUtils.toMessageChain(message, isTemplate));
    }

    /**
     * 发送群组消息
     *
     * @param id      群号
     * @param message 消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendGroupMessage(long id, @Nullable MessageChain message) {
        return sendGroupMessage(bot.getMiraiBot().getGroupOrFail(id), message);
    }

    /**
     * 发送群组消息
     *
     * @param group   群对象
     * @param message 消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendGroupMessage(@NotNull Group group, @Nullable String message) {
        return sendGroupMessage(group, message, PicqConfig.getInstance().isDefaultSendUseMessageTemplate());
    }

    /**
     * 发送群组消息
     *
     * @param group      群对象
     * @param message    消息
     * @param isTemplate 是否消息模板
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendGroupMessage(
        @NotNull Group group,
        @Nullable String message,
        boolean isTemplate
    ) {
        return sendGroupMessage(group, MsgUtils.toMessageChain(message, isTemplate));
    }

    /**
     * 发送群组消息
     *
     * @param group   群对象
     * @param message 消息
     * @return 消息回执。如果是 {@code null} 则表示消息被拦截或传入的消息为空。
     */
    public @Nullable MessageReceipt<Contact> sendGroupMessage(@NotNull Group group, @Nullable MessageChain message) {
        return sendMessage(group, message);
    }

    /**
     * 发送消息给任意对象（好友、群员、群）
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
