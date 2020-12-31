package cc.moecraft.icq.event;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.events.message.EventMessage;
import net.mamoe.mirai.event.events.BotEvent;

public abstract class Event {
    protected final BotEvent miraiEvent;

    protected final PicqBotX bot;

    /**
     * 因为 Mirai 只会记录消息事件的时间，所以需要使用 {@link EventMessage#getEventTime()}。
     * 这个字段对于其它事件都会是 0
     */
    protected final int time;

    public Event(BotEvent miraiEvent, PicqBotX bot, int time) {
        this.miraiEvent = miraiEvent;
        this.time = time;
        this.bot = bot;
    }

    public BotEvent getMiraiEvent() {
        return miraiEvent;
    }

    public PicqBotX getBot() {
        return bot;
    }

    /**
     * 因为 Mirai 只会记录消息事件的时间，所以需要使用 {@link EventMessage#getEventTime()}。
     * 这个字段对于其它事件都会是 0
     */
    @Deprecated
    public int getTime() {
        return time;
    }
}
