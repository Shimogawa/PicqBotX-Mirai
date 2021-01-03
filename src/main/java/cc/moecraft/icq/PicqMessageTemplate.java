package cc.moecraft.icq;

import cc.moecraft.icq.utils.StringUtils;
import net.mamoe.mirai.message.data.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息模板工具
 */
public final class PicqMessageTemplate {
    private static boolean hasInitiated = false;
    private static final Timer timer = new Timer(true);
    private static final ConcurrentHashMap<String, WeakReference<MessageContent>> richObjRefMap = new ConcurrentHashMap<>();

    private PicqMessageTemplate() {
    }

    /**
     * 初始化消息模板
     */
    static void init() {
        if (hasInitiated) return;
        timer.schedule(
            new TimerTask() {
                @Override
                public void run() {
                    System.gc();
                    LinkedList<String> shouldRemoveList = new LinkedList<>();
                    for (String k : richObjRefMap.keySet()) {
                        if (richObjRefMap.get(k).get() == null) {
                            shouldRemoveList.add(k);
                        }
                    }
                    for (String k : shouldRemoveList) {
                        richObjRefMap.remove(k);
                    }
                }
            },
            PicqConfig.getInstance().getScheduledClearWeakRefTimeInterval() * 1000L,
            PicqConfig.getInstance().getScheduledClearWeakRefTimeInterval() * 1000L
        );
        hasInitiated = true;
    }

    /**
     * 将非文本对象转换为消息模板字符串，例如把图片转换为 %12345678%
     *
     * @param messageContent 非文本对象
     * @return 消息模板
     */
    public static @NotNull String allocateRichObj(@NotNull MessageContent messageContent) {
        if (messageContent instanceof PlainText) {
            throw new IllegalArgumentException("必须不是文本消息。");
        }
        String id;
        do {
            id = StringUtils.getRandomStr(PicqConstants.MESSAGE_TMPL_LENGTH);
        } while (richObjRefMap.containsKey(id));
        richObjRefMap.put(id, new WeakReference<>(messageContent));
        return id;
    }

    /**
     * 用消息模板获取非文本对象。可以带%，也可以不带。
     *
     * @param id 消息模板或对象 id
     * @return 非文本对象，如果是 {@code null} 则这个对象已经被垃圾回收或不存在
     */
    public static @Nullable MessageContent getRichObj(@NotNull String id) {
        if (id.length() == PicqConstants.MESSAGE_TMPL_LENGTH) {
            WeakReference<MessageContent> mc = richObjRefMap.get(id);
            return mc == null ? null : mc.get();
        }
        if (id.length() == PicqConstants.MESSAGE_TMPL_LENGTH + 2) {
            WeakReference<MessageContent> mc = richObjRefMap.get(id.substring(1, id.length() - 1));
            return mc == null ? null : mc.get();
        }
        return null;
    }

    /**
     * 转换为消息模板
     *
     * @param chain 消息链
     * @return 消息模板
     */
    public static String toMessageTemplate(MessageChain chain) {
        StringBuilder sb = new StringBuilder();
        for (SingleMessage sm : chain) {
            if (sm instanceof MessageMetadata) continue;
            if (sm instanceof PlainText) {
                sb.append(escape(((PlainText) sm).getContent()));
            } else {
                String id = allocateRichObj((MessageContent) sm);
                sb.append(PicqConstants.INTERPOLE_CHAR)
                    .append(id)
                    .append(PicqConstants.INTERPOLE_CHAR);
            }
        }
        return sb.toString();
    }

    /**
     * 把消息模板转化为普通字符串，去除转义
     *
     * @param messageTemplate 消息模板
     * @return 普通字符串
     */
    public static String toSimpleString(String messageTemplate) {
        return unescape(messageTemplate);
    }

    /**
     * 把消息模板转化为 {@link MessageChain}
     *
     * @param messageTemplate 消息模板
     * @return 消息链
     */
    public static MessageChain messageTemplateToChain(String messageTemplate) {
        return MessageUtils.newChain(messageTemplateToList(messageTemplate));
    }

    /**
     * 把消息模板转化为 {@link MessageContent} 列表。
     * 使用 {@link #messageTemplateToChain(String)} 转化为消息链
     *
     * @param messageTemplate 消息模板
     * @return 消息内容列表
     */
    public static List<MessageContent> messageTemplateToList(String messageTemplate) {
        if (messageTemplate.length() <= 1)
            return Collections.singletonList(new PlainText(messageTemplate));
        StringBuilder sb = new StringBuilder();
        LinkedList<MessageContent> msgList = new LinkedList<>();
        int idx = 0;
        while (idx < messageTemplate.length()) {
            char curChar = messageTemplate.charAt(idx);
            switch (curChar) {
                case PicqConstants.ESCAPE_CHAR: {
                    idx++;
                    if (idx >= messageTemplate.length()) {
                        sb.append(PicqConstants.ESCAPE_CHAR);
                        msgList.add(new PlainText(sb.toString()));
                        break;
                    }
                    char nextChar = messageTemplate.charAt(idx);
                    switch (nextChar) {
                        case PicqConstants.ESCAPE_CHAR: {
                            sb.append(PicqConstants.ESCAPE_CHAR);
                            break;
                        }
                        case PicqConstants.INTERPOLE_CHAR: {
                            sb.append(PicqConstants.INTERPOLE_CHAR);
                            break;
                        }
                        default: {
                            sb.append(PicqConstants.ESCAPE_CHAR).append(nextChar);
                        }
                    }
                    break;
                }
                case PicqConstants.INTERPOLE_CHAR: {
                    if (messageTemplate.length() - idx <= PicqConstants.MESSAGE_TMPL_LENGTH) {
                        sb.append(PicqConstants.INTERPOLE_CHAR);
                        break;
                    }
                    if (messageTemplate.charAt(idx + PicqConstants.MESSAGE_TMPL_LENGTH + 1) == PicqConstants.INTERPOLE_CHAR) {
                        String substr = messageTemplate.substring(idx + 1, idx + PicqConstants.MESSAGE_TMPL_LENGTH + 1);
                        MessageContent mc = getRichObj(substr);
                        if (mc == null) {
                            sb.append(PicqConstants.INTERPOLE_CHAR);
                            break;
                        }
                        if (sb.length() != 0) {
                            msgList.add(new PlainText(sb.toString()));
                            sb.setLength(0);
                        }
                        msgList.add(mc);
                        idx += PicqConstants.MESSAGE_TMPL_LENGTH + 1;
                    }
                    break;
                }
                default: {
                    sb.append(curChar);
                }
            }
            idx++;
        }
        if (sb.length() != 0) {
            msgList.add(new PlainText(sb.toString()));
        }
        return msgList;
    }

    private static String escape(String original) {
        return original
            .replace(
                Character.toString(PicqConstants.ESCAPE_CHAR),
                new String(new char[]{PicqConstants.ESCAPE_CHAR, PicqConstants.ESCAPE_CHAR})
            )
            .replace(
                Character.toString(PicqConstants.INTERPOLE_CHAR),
                new String(new char[]{PicqConstants.ESCAPE_CHAR, PicqConstants.INTERPOLE_CHAR})
            );
    }

    private static String unescape(String msgTmpl) {
        if (msgTmpl.length() <= 1) return msgTmpl;
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < msgTmpl.length()) {
            char curChar = msgTmpl.charAt(idx);
            if (curChar == PicqConstants.ESCAPE_CHAR) {
                idx++;
                if (idx >= msgTmpl.length()) {
                    sb.append(PicqConstants.ESCAPE_CHAR);
                    break;
                }
                char nextChar = msgTmpl.charAt(idx);
                switch (nextChar) {
                    case PicqConstants.ESCAPE_CHAR:
                    case PicqConstants.INTERPOLE_CHAR:
                        sb.append(nextChar);
                        break;
                    default:
                        sb.append(PicqConstants.ESCAPE_CHAR).append(nextChar);
                }
            } else {
                sb.append(curChar);
            }
            idx++;
        }
        return sb.toString();
    }
}
