package cc.moecraft.icq.event;

import cc.moecraft.icq.PicqBotX;

public class Event {
    protected Long time;

    protected PicqBotX bot;

    public Event(Long time, PicqBotX bot) {
        this.time = time;
        this.bot = bot;
    }
}
