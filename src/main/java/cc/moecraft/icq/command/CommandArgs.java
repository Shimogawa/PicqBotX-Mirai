package cc.moecraft.icq.command;

import cc.moecraft.icq.command.interfaces.IcqCommand;

import java.util.ArrayList;

public final class CommandArgs {
    private final String prefix;

    private final String commandName;

    private final IcqCommand commandRunner;

    private final ArrayList<String> args;

    public CommandArgs(String prefix, String commandName, IcqCommand commandRunner, ArrayList<String> args) {
        this.prefix = prefix;
        this.commandName = commandName;
        this.commandRunner = commandRunner;
        this.args = args;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getCommandName() {
        return commandName;
    }

    public IcqCommand getCommandRunner() {
        return commandRunner;
    }

    public ArrayList<String> getArgs() {
        return args;
    }
}
