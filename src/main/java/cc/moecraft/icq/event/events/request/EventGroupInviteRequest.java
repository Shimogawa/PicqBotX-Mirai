package cc.moecraft.icq.event.events.request;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent;

public final class EventGroupInviteRequest extends EventRequest {
    private final Friend invitor;

    public EventGroupInviteRequest(BotInvitedJoinGroupRequestEvent miraiEvent, PicqBotX bot) {
        super(miraiEvent, bot, miraiEvent.getInvitorId(), miraiEvent.getInvitorNick(), "");
        this.invitor = miraiEvent.getInvitor();
    }

    public Friend getInvitor() {
        return invitor;
    }

    @Override
    public void accept() {
        ((BotInvitedJoinGroupRequestEvent) miraiEvent).accept();
    }

    /**
     * 注：目前无法拒绝加群邀请
     */
    @Override
    public void reject() {
    }
}
