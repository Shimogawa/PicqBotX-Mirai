package cc.moecraft.icq.event;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.event.events.BotEvent;

public abstract class Event {
    protected BotEvent miraiEvent;

    protected PicqBotX bot;

    protected int time;

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

    public int getTime() {
        return time;
    }
}
