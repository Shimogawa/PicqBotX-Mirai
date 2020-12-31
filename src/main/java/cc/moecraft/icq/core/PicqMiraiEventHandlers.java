package cc.moecraft.icq.core;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.EventManager;
import cc.moecraft.icq.event.events.message.*;
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
    public void onReceiveFriendRecall(@NotNull MessageRecallEvent.FriendRecall friendRecall) {
        eventManager.call(new EventNoticeFriendRecall(friendRecall, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveGroupRecall(@NotNull MessageRecallEvent.GroupRecall groupRecall) {
        eventManager.call(new EventNoticeGroupRecall(groupRecall, bot));
    }
}
