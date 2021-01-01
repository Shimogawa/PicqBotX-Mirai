package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import kotlin.DeprecationLevel;
import kotlin.NotImplementedError;
import net.mamoe.mirai.event.events.BotEvent;

@java.lang.Deprecated
@kotlin.Deprecated(message = "不要使用！Mirai 没有提供这个接口。", level = DeprecationLevel.ERROR)
public class EventNoticeGroupUpload extends EventNotice {
    public EventNoticeGroupUpload(BotEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getBot().getAsFriend());
        throw new NotImplementedError("Mirai 没有实现这个功能。");
    }
}
