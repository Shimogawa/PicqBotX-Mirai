package cc.moecraft.icq.command.interfaces;

import cc.moecraft.icq.event.events.message.EventPrivateMessage;
import cc.moecraft.icq.event.events.message.EventTempMessage;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Member;

import java.util.ArrayList;

/**
 * 私聊（好友 或 临时会话）指令
 */
public interface PrivateCommand extends IcqCommand {
    /**
     * 执行好友聊天指令
     *
     * @param event   好友消息事件
     * @param sender  发送者的用户
     * @param command 指令名 ( 不包含指令参数 )
     * @param args    指令参数 ( 不包含指令名 )
     * @return 发送回去的消息模板 ( 当然也可以手动发送然后返回空 )
     */
    String privateMessage(
        EventPrivateMessage event,
        Friend sender,
        String command,
        ArrayList<String> args
    );

    /**
     * 执行临时会话指令。默认为不执行任何动作
     *
     * @param event   临时消息事件
     * @param sender  发送者用户
     * @param command 指令名 ( 不包含指令参数 )
     * @param args    指令参数 ( 不包含指令名 )
     * @return 发送回去的消息模板 ( 当然也可以手动发送然后返回空 )
     */
    default String tempMessage(
        EventTempMessage event,
        Member sender,
        String command,
        ArrayList<String> args
    ) {
        return null;
    }
}
