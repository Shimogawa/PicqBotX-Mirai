package cc.moecraft.icq.event.events.request;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.event.events.NewFriendRequestEvent;

public final class EventFriendRequest extends EventRequest {
    public EventFriendRequest(NewFriendRequestEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getFromId(), miraiEvent.getFromNick(), miraiEvent.getMessage());
    }

    @Override
    public void accept() {
        ((NewFriendRequestEvent) miraiEvent).accept();
    }

    @Override
    public void reject() {
        ((NewFriendRequestEvent) miraiEvent).reject(false);
    }

    public void reject(boolean blacklist) {
        ((NewFriendRequestEvent) miraiEvent).reject(blacklist);
    }
}
