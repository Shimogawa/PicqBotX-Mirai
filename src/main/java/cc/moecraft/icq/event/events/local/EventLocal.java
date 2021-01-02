package cc.moecraft.icq.event.events.local;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.Event;
import net.mamoe.mirai.event.events.BotEvent;

public class EventLocal extends Event {
    protected boolean cancelled = false;

    public EventLocal(BotEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
