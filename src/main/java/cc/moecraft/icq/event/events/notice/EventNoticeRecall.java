package cc.moecraft.icq.event.events.notice;

import cc.moecraft.icq.PicqBotX;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.events.MessageRecallEvent;

/**
 * 撤回消息事件
 */
public abstract class EventNoticeRecall extends EventNotice {
    /**
     * 撤回消息的人
     */
    protected final User operator;

    /**
     * 消息发送者
     */
    protected final User author;

    protected final int eventTime;

    public EventNoticeRecall(MessageRecallEvent miraiEvent, PicqBotX bot, User operator) {
        super(miraiEvent, bot, miraiEvent.getMessageTime(), operator);
        this.operator = operator;
        if (miraiEvent.getAuthor() == null) {
            this.author = null;
        } else {
            this.author = miraiEvent.getAuthor() instanceof Bot
                ? ((Bot) miraiEvent.getAuthor()).getAsFriend()
                : (User) miraiEvent.getAuthor();
        }
        this.eventTime = miraiEvent.getMessageTime();
    }

    /**
     * 获取撤回消息的人
     *
     * @return 撤回消息的人
     */
    public User getOperator() {
        return operator;
    }

    /**
     * 获取消息发送者
     *
     * @return 消息发送者
     */
    public User getAuthor() {
        return author;
    }

    /**
     * 获取事件时间
     *
     * @return 事件时间
     */
    public int getEventTime() {
        return eventTime;
    }
}
