package cc.moecraft.icq.utils;

import cc.moecraft.icq.PicqMessageTemplate;
import cn.hutool.core.util.StrUtil;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.message.data.PlainText;
import org.jetbrains.annotations.Nullable;

public class MsgUtils {
    private MsgUtils() {
    }

    /**
     * 字符串转成消息链
     *
     * @param message    消息
     * @param isTemplate 是否是消息模板
     * @return 消息链（<code>null</code> 表示消息为空）
     */
    public static @Nullable MessageChain toMessageChain(@Nullable String message, boolean isTemplate) {
        if (StrUtil.isEmpty(message)) return null;
        return isTemplate
            ? PicqMessageTemplate.messageTemplateToChain(message)
            : MessageUtils.newChain(new PlainText(message));
    }
}
