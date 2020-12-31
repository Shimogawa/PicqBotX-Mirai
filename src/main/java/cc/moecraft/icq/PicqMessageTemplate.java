package cc.moecraft.icq;

import cc.moecraft.icq.utils.StringUtils;
import net.mamoe.mirai.message.data.*;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PicqMessageTemplate {
    private static final Timer timer = new Timer(true);
    private static ConcurrentHashMap<String, WeakReference<MessageContent>> richObjRefMap = new ConcurrentHashMap<>();

    public static void init() {
        timer.schedule(
            new TimerTask() {
                @Override
                public void run() {
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
            PicqConfig.getInstance().getScheduledClearWeakRefTimeInterval(),
            PicqConfig.getInstance().getScheduledClearWeakRefTimeInterval()
        );
    }

    public static @Nullable MessageContent getRichObj(String id) {
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

    public static String toMessageTemplate(MessageChain chain) {
        StringBuilder sb = new StringBuilder();
        for (SingleMessage sm : chain) {
            if (sm instanceof MessageMetadata) continue;
            if (sm instanceof PlainText) {
                sb.append(escape(((PlainText) sm).getContent()));
            } else {
                String id = StringUtils.getRandomStr(PicqConstants.MESSAGE_TMPL_LENGTH);
                richObjRefMap.put(id, new WeakReference<>((MessageContent) sm));
                sb.append('%').append(id).append('%');
            }
        }
        return sb.toString();
    }

    public static String toSimpleString(String messageTemplate) {
        return unescape(messageTemplate);
    }

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
