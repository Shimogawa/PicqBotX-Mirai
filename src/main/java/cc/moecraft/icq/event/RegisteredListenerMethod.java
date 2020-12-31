package cc.moecraft.icq.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegisteredListenerMethod
{
    private final Method method;

    private final EventListener listener;

    public RegisteredListenerMethod(Method method, EventListener listener) {
        this.method = method;
        this.listener = listener;
    }

    /**
     * 反射执行监听方法
     *
     * @param event 事件
     * @throws InvocationTargetException 反射失败
     * @throws IllegalAccessException 无访问权限 (不可能发生)
     */
    public void call(Event event) throws InvocationTargetException, IllegalAccessException
    {
        method.setAccessible(true);
        method.invoke(listener, event);
    }

    public Method getMethod() {
        return method;
    }

    public EventListener getListener() {
        return listener;
    }
}
