package cc.moecraft.icq.event.events.local;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.Event;
import net.mamoe.mirai.event.events.BotEvent;

public final class EventLocalException extends EventLocal {
    private final Throwable exception;

    private final Event parentEvent;

    public EventLocalException(BotEvent miraiEvent, PicqBotX bot, Throwable exception, Event parentEvent) {
        super(miraiEvent, bot);
        this.exception = exception;
        this.parentEvent = parentEvent;
    }

    public Throwable getException() {
        return exception;
    }

    public Event getParentEvent() {
        return parentEvent;
    }
}
