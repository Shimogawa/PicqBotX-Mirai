package cc.moecraft.icq.event.events.request;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.MemberJoinRequestEvent;

public final class EventGroupAddRequest extends EventRequest {
    private final Group group;

    public EventGroupAddRequest(MemberJoinRequestEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getFromId(), miraiEvent.getFromNick(), miraiEvent.getMessage());
        this.group = miraiEvent.getGroup();
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public void accept() {
        ((MemberJoinRequestEvent) miraiEvent).accept();
    }

    @Override
    public void reject() {
        ((MemberJoinRequestEvent) miraiEvent).reject(false, "");
    }

    public void reject(boolean blacklist, String message) {
        ((MemberJoinRequestEvent) miraiEvent).reject(blacklist, message);
    }
}
