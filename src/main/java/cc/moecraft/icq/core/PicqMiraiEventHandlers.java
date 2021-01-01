package cc.moecraft.icq.core;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.EventManager;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import cc.moecraft.icq.event.events.message.EventTempMessage;
import cc.moecraft.icq.event.events.notice.*;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.*;
import org.jetbrains.annotations.NotNull;

public class PicqMiraiEventHandlers extends SimpleListenerHost {

    private final EventManager eventManager;

    private final PicqBotX bot;

    public PicqMiraiEventHandlers(EventManager eventManager) {
        this.eventManager = eventManager;
        this.bot = eventManager.getBot();
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveFriendMessageEvent(@NotNull FriendMessageEvent friendMessageEvent) {
        eventManager.call(new EventPrivateMessage(friendMessageEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveFriendGroupMessageEvent(@NotNull GroupMessageEvent groupMessageEvent) {
        eventManager.call(new EventGroupMessage(groupMessageEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveTempMessageEvent(@NotNull TempMessageEvent tempMessageEvent) {
        eventManager.call(new EventTempMessage(tempMessageEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveFriendAddEvent(@NotNull FriendAddEvent friendAddEvent) {
        eventManager.call(new EventNoticeFriendAdd(friendAddEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveFriendNudgedEvent(@NotNull FriendNudgedEvent friendNudgedEvent) {
        eventManager.call(new EventNoticeFriendPoke(friendNudgedEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveMemberNudgedEvent(@NotNull MemberNudgedEvent memberNudgedEvent) {
        eventManager.call(new EventNoticeGroupPoke(memberNudgedEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveBotNudgedEvent(@NotNull BotNudgedEvent botNudgedEvent) {
        if (botNudgedEvent instanceof BotNudgedEvent.InPrivateSession) {
            eventManager.call(new EventNoticeFriendPoke(
                (BotNudgedEvent.InPrivateSession) botNudgedEvent,
                bot
            ));
        } else if (botNudgedEvent instanceof BotNudgedEvent.InGroup) {
            eventManager.call(new EventNoticeGroupPoke(
                (BotNudgedEvent.InGroup) botNudgedEvent,
                bot
            ));
        }
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveMessageRecall(@NotNull MessageRecallEvent messageRecallEvent) {
        if (messageRecallEvent instanceof MessageRecallEvent.FriendRecall) {
            eventManager.call(new EventNoticeFriendRecall(
                (MessageRecallEvent.FriendRecall) messageRecallEvent,
                bot
            ));
        } else if (messageRecallEvent instanceof MessageRecallEvent.GroupRecall) {
            eventManager.call(new EventNoticeGroupRecall(
                (MessageRecallEvent.GroupRecall) messageRecallEvent,
                bot
            ));
        }
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveMemberMuteEvent(@NotNull MemberMuteEvent memberMuteEvent) {
        eventManager.call(new EventNoticeGroupBan(memberMuteEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveBotMuteEvent(@NotNull BotMuteEvent botMuteEvent) {
        eventManager.call(new EventNoticeGroupBan(botMuteEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveMemberUnmuteEvent(@NotNull MemberUnmuteEvent memberUnmuteEvent) {
        eventManager.call(new EventNoticeGroupBan(memberUnmuteEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveBotUnmuteEvent(@NotNull BotUnmuteEvent botUnmuteEvent) {
        eventManager.call(new EventNoticeGroupBan(botUnmuteEvent, bot));
    }
}
