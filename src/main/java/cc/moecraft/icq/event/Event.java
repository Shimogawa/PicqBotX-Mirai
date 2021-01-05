package cc.moecraft.icq.event;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.event.events.notice.EventNoticeRecall;
import net.mamoe.mirai.event.events.BotEvent;

/**
 * 表示一个事件
 */
public abstract class Event {
    /**
     * 原始事件。对于 {@link cc.moecraft.icq.event.events.local.EventLocalSendMessage} 事件，这个字段是 {@code null}
     */
    protected final BotEvent miraiEvent;

    /**
     * 机器人对象
     */
    protected final PicqBotX bot;

    /**
     * 事件发生的时间
     * <p>
     * 因为 Mirai 只会记录消息事件的时间，所以需要使用 {@link EventMessage#getEventTime()}
     * 以及其它事件中的 {@code getEventTime} 方法。
     * 这个字段对于其它事件都会是 0
     *
     * @see EventMessage#getEventTime()
     * @see EventNoticeRecall#getEventTime()
     */
    @Deprecated
    protected final int time;

    public Event(BotEvent miraiEvent, PicqBotX bot, int time) {
        this.miraiEvent = miraiEvent;
        this.time = time;
        this.bot = bot;
    }

    /**
     * 获取原始事件
     *
     * @return 原始事件。对于 {@link cc.moecraft.icq.event.events.local.EventLocalSendMessage} 事件，这个字段是 {@code null}
     */
    public BotEvent getMiraiEvent() {
        return miraiEvent;
    }

    /**
     * 获取机器人对象
     *
     * @return 机器人对象
     */
    public PicqBotX getBot() {
        return bot;
    }

    /**
     * 获取事件发生的时间
     * <p>
     * 因为 Mirai 只会记录消息事件的时间，所以需要使用 {@link EventMessage#getEventTime()}
     * 以及其它事件中的 {@code getEventTime} 方法。
     * 这个字段对于其它事件都会是 0
     *
     * @return 事件发生的时间
     * @see EventMessage#getEventTime()
     * @see EventNoticeRecall#getEventTime()
     */
    @Deprecated
    public int getTime() {
        return time;
    }
}
