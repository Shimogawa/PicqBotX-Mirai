package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import kotlin.DeprecationLevel;
import kotlin.NotImplementedError;
import net.mamoe.mirai.event.events.BotEvent;

/**
 * 上传群文件事件
 *
 * <h2>不要使用！</h2>
 * Mirai 没有提供这个接口
 */
@java.lang.Deprecated
@kotlin.Deprecated(message = "不要使用！Mirai 没有提供这个接口。", level = DeprecationLevel.ERROR)
public final class EventNoticeGroupUpload extends EventNotice {
    public EventNoticeGroupUpload(BotEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getBot().getAsFriend());
        throw new NotImplementedError("Mirai 没有实现这个功能。");
    }
}
