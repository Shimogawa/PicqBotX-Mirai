package cc.moecraft.icq.utils;

import cc.moecraft.icq.PicqMessageTemplate;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;

public class MsgUtils {
    private MsgUtils() { }

    public static MessageChain toMessageChain(String message, boolean isTemplate) {
        return isTemplate
            ? PicqMessageTemplate.messageTemplateToChain(message)
            : MessageUtils.newChain(new PlainText(message));
    }
}
