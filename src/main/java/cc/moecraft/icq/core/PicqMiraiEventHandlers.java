package cc.moecraft.icq.core;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.EventManager;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
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
}
