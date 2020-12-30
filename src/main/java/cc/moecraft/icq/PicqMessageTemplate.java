package cc.moecraft.icq;

import cc.moecraft.icq.utils.StringUtils;
import net.mamoe.mirai.message.data.*;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class PicqMessageTemplate {
    private static final Timer timer = new Timer(true);
    private static ConcurrentHashMap<String, WeakReference<MessageContent>> richObjRefMap = new ConcurrentHashMap<>();

    public static void init(long interval) {
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
            interval,
            interval
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

    private static String escape(String original) {
        return original
            .replace("\\", "\\\\")
            .replace(
                Character.toString(PicqConstants.INTERPOLE_CHAR),
                new String(new char[]{'\\', PicqConstants.INTERPOLE_CHAR})
            );
    }
}
