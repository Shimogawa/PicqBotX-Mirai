package cc.moecraft.icq.core;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqMessageTemplate;
import cc.moecraft.icq.event.events.local.EventLocalSendGroupMessage;
import cc.moecraft.icq.event.events.local.EventLocalSendMessage;
import cc.moecraft.icq.event.events.local.EventLocalSendPrivateMessage;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unchecked")
public class MiraiApi {
    private final PicqBotX bot;

    public MiraiApi(PicqBotX bot) {
        this.bot = bot;
    }

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, String message, boolean isTemplate) {
        return sendMessage(bot.getMiraiBot().getFriendOrFail(id),
            isTemplate
                ? PicqMessageTemplate.messageTemplateToChain(message)
                : MessageUtils.newChain(new PlainText(message))
        );
    }

    public @Nullable MessageReceipt<Contact> sendPrivateMessage(long id, MessageChain message) {
        return sendMessage(bot.getMiraiBot().getFriendOrFail(id), message);
    }

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
