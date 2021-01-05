package cc.moecraft.icq.event.events.local;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.Event;
import net.mamoe.mirai.event.events.BotEvent;

/**
 * 本地触发的事件
 */
public class EventLocal extends Event {
    protected boolean cancelled = false;

    public EventLocal(BotEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0);
    }

    /**
     * 获取是否被取消
     *
     * @return 是否被取消
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * 设置是否取消处理这个事件
     *
     * @param cancelled 是否取消处理这个事件
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
