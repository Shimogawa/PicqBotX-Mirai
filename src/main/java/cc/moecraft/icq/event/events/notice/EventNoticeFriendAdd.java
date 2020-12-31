package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.event.events.FriendAddEvent;

public final class EventNoticeFriendAdd extends EventNotice {
    public EventNoticeFriendAdd(FriendAddEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, 0, miraiEvent.getFriend());
    }
}
