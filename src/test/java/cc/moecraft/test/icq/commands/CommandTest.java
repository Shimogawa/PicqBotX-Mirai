package cc.moecraft.test.icq.commands;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import net.mamoe.mirai.contact.User;

import java.util.ArrayList;

public class CommandTest implements EverywhereCommand {
    @Override
    public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
        return "测试返回值 - 用户信息: " + sender.toString();
    }

    @Override
    public CommandProperties properties() {
        return new CommandProperties("test", "测试", "测试2");
    }
}
