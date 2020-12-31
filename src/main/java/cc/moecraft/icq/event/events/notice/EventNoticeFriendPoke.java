package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.BotNudgedEvent;
import net.mamoe.mirai.event.events.FriendNudgedEvent;
import net.mamoe.mirai.event.events.MemberNudgedEvent;

public final class EventNoticeFriendPoke extends EventNotice {
    private final long targetId;

    private final User target;

    public EventNoticeFriendPoke(FriendNudgedEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getFrom());
        this.targetId = miraiEvent.getFrom().getId();
        this.target = miraiEvent.getFrom();
    }

    public EventNoticeFriendPoke(MemberNudgedEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getFrom());
        this.target = miraiEvent.getMember();
        this.targetId = target.getId();
    }

    public EventNoticeFriendPoke(BotNudgedEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0,
            miraiEvent.getFrom() instanceof Bot
                ? ((Bot) miraiEvent.getFrom()).getAsFriend()
                : (User) miraiEvent.getFrom()
        );
        this.target = miraiEvent.getBot().getAsFriend();
        this.targetId = target.getId();
    }

    public long getTargetId() {
        return targetId;
    }

    public User getTarget() {
        return target;
    }
}
