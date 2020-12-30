package cc.moecraft.icq.core;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.EventManager;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.*;
import org.jetbrains.annotations.NotNull;

public class PicqMiraiEventHandlers extends SimpleListenerHost {

    private final EventManager eventManager;

    public PicqMiraiEventHandlers(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {

    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveFriendMessageEvent(@NotNull FriendMessageEvent friendMessageEvent) {
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveBotEvent(@NotNull BotActiveEvent botEvent) {

    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveGroupEvent(@NotNull GroupEvent groupEvent) {

    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveFriendEvent(@NotNull FriendEvent friendEvent) {

    }
}
