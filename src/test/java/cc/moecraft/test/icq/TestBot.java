package cc.moecraft.test.icq;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.test.icq.commands.CommandTest;
import cc.moecraft.test.icq.listeners.TestListener;

import java.util.Scanner;

public class TestBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("QQ:");
        String qq = sc.nextLine();
        String password;
        System.out.println("Password:");
        if (System.console() == null) {
            password = sc.nextLine();
        } else {
            password = new String(System.console().readPassword());
        }

        PicqConfig.modifyConfig(it -> {
            it.setScheduledClearWeakRefTimeInterval(60);
        });
        PicqBotX bot = new PicqBotX();
        bot.setAccount(Long.parseLong(qq), password);
        bot.getEventManager().registerListeners(
            new TestListener()
        );
        bot.enableCommandManager("!", "/");
        bot.getCommandManager().registerCommand(
            new CommandTest()
        );

        bot.getLoggerManager().getLoggerInstance("Test", false).log(
            bot.getCommandManager().getCommands().toString()
        );

        bot.startBot();
    }
}
