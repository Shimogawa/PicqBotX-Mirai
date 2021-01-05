package cc.moecraft.icq.event.events.local;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.Event;
import net.mamoe.mirai.event.events.BotEvent;

/**
 * 本地处理事件时报错事件
 */
public final class EventLocalException extends EventLocal {
    private final Throwable exception;

    private final Event parentEvent;

    public EventLocalException(BotEvent miraiEvent, PicqBotX bot, Throwable exception, Event parentEvent) {
        super(miraiEvent, bot);
        this.exception = exception;
        this.parentEvent = parentEvent;
    }

    /**
     * 获取抛出的异常
     *
     * @return 抛出的异常
     */
    public Throwable getException() {
        return exception;
    }

    /**
     * 获取发生异常的事件
     *
     * @return 发生异常的事件
     */
    public Event getParentEvent() {
        return parentEvent;
    }
}
