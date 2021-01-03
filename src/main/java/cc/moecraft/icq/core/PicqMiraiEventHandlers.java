package cc.moecraft.icq.core;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.event.EventManager;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import cc.moecraft.icq.event.events.message.EventTempMessage;
import cc.moecraft.icq.event.events.notice.*;
import cc.moecraft.icq.event.events.notice.groupadmin.EventNoticeGroupAdminChange;
import cc.moecraft.icq.event.events.notice.groupmember.decrease.EventNoticeGroupMemberKick;
import cc.moecraft.icq.event.events.notice.groupmember.decrease.EventNoticeGroupMemberLeave;
import cc.moecraft.icq.event.events.notice.groupmember.increase.EventNoticeGroupMemberApprove;
import cc.moecraft.icq.event.events.notice.groupmember.increase.EventNoticeGroupMemberInvite;
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

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveMemberPermissionChangeEvent(@NotNull MemberPermissionChangeEvent memberPermissionChangeEvent) {
        eventManager.call(new EventNoticeGroupAdminChange(memberPermissionChangeEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveBotGroupPermissionChangeEvent(@NotNull BotGroupPermissionChangeEvent botGroupPermissionChangeEvent) {
        eventManager.call(new EventNoticeGroupAdminChange(botGroupPermissionChangeEvent, bot));
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveMemberJoinEvent(@NotNull MemberJoinEvent memberJoinEvent) {
        if (memberJoinEvent instanceof MemberJoinEvent.Invite) {
            eventManager.call(new EventNoticeGroupMemberInvite(((MemberJoinEvent.Invite) memberJoinEvent), bot));
        } else if (memberJoinEvent instanceof MemberJoinEvent.Active) {
            eventManager.call(new EventNoticeGroupMemberApprove(((MemberJoinEvent.Active) memberJoinEvent), bot));
        }
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveBotJoinGroupEvent(@NotNull BotJoinGroupEvent botJoinGroupEvent) {
        if (botJoinGroupEvent instanceof BotJoinGroupEvent.Invite) {
            eventManager.call(new EventNoticeGroupMemberInvite((BotJoinGroupEvent.Invite) botJoinGroupEvent, bot));
        } else if (botJoinGroupEvent instanceof BotJoinGroupEvent.Active) {
            eventManager.call(new EventNoticeGroupMemberApprove((BotJoinGroupEvent.Active) botJoinGroupEvent, bot));
        }
    }

    @net.mamoe.mirai.event.EventHandler
    public void onReceiveMemberLeaveEvent(@NotNull MemberLeaveEvent memberLeaveEvent) {
        if (memberLeaveEvent instanceof MemberLeaveEvent.Kick) {
            eventManager.call(new EventNoticeGroupMemberKick(((MemberLeaveEvent.Kick) memberLeaveEvent), bot));
        } else if (memberLeaveEvent instanceof MemberLeaveEvent.Quit) {
            eventManager.call(new EventNoticeGroupMemberLeave(((MemberLeaveEvent.Quit) memberLeaveEvent), bot));
        }
    }
}
