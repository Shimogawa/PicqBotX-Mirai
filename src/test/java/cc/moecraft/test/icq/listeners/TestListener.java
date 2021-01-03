package cc.moecraft.test.icq.listeners;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.EventListener;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;

public class TestListener implements EventListener {
    @EventHandler
    public void onPMEvent(EventPrivateMessage epm) {
        String tmpl = epm.getMessage();
        System.out.println(tmpl);
        epm.respond(epm.getMessage(), true);
    }
}
