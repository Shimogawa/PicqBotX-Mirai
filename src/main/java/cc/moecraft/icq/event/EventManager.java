package cc.moecraft.icq.event;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.command.CommandListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import kotlin.NotImplementedError;

import java.lang.reflect.Method;
import java.util.*;

public class EventManager {
    private final PicqBotX bot;

    private CommandListener commandListener = null;

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final ArrayList<EventListener> registeredListeners = new ArrayList<>();

    private final HashMap<String, ArrayList<RegisteredListenerMethod>> registeredMethods = new HashMap<>();

    private static final List<Class<? extends Event>> eventClasses = Arrays.asList(
        EventGroupMessage.class,
        EventPrivateMessage.class,
        EventMessage.class
    );

    public EventManager(PicqBotX bot) {
        this.bot = bot;
    }

    /**
     * 执行事件
     *
     * @param event 事件对象
     */
    public void call(Event event) {
        if (event instanceof EventMessage && commandListener != null) {
            if (!commandListener.check((EventMessage) event)) {
                return;
            }
        }
        String mapKey = event.getClass().getName();
        if (!registeredMethods.containsKey(mapKey)) {
            return;
        }
        for (RegisteredListenerMethod method : registeredMethods.get(mapKey)) {
            try {
                method.call(event);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Throwable e) {
                callError(event, e);
            }
        }
    }

    /**
     * 注册一个事件监听器
     *
     * @param listener 监听器
     */
    public void registerListener(EventListener listener) {
        registeredListeners.add(listener);
        for (Method method : listener.getClass().getMethods()) {
            if (method.getParameterCount() != 1) {
                continue;
            }
            Class<?> event = method.getParameterTypes()[0];
            if (!Event.class.isAssignableFrom(event)) {
                continue;
            }
            if (!method.isAnnotationPresent(EventHandler.class)) {
                continue;
            }
            for (Class<? extends Event> eventClass : eventClasses) {
                if (!event.isAssignableFrom(eventClass)) {
                    continue;
                }
                String mapKey = eventClass.getName();
                if (registeredMethods.containsKey(mapKey)) {
                    registeredMethods.get(mapKey).add(new RegisteredListenerMethod(method, listener));
                } else {
                    registeredMethods.put(mapKey, new ArrayList<>(
                        Collections.singletonList(new RegisteredListenerMethod(method, listener))
                    ));
                }
            }
        }
    }

    /**
     * 注册很多个事件监听器
     *
     * @param listeners 很多个监听器
     */
    public void registerListeners(EventListener... listeners)
    {
        for (EventListener listener : listeners)
        {
            registerListener(listener);
        }
    }

    public void callError(Event event, Throwable throwable)
    {
        // TODO
        throw new NotImplementedError("Call error");
    }

    public PicqBotX getBot() {
        return bot;
    }

    public CommandListener getCommandListener() {
        return commandListener;
    }

    public void setCommandListener(CommandListener commandListener) {
        this.commandListener = commandListener;
    }
}
