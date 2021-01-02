package cc.moecraft.icq.command;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandProperties {
    private final String name;

    private final ArrayList<String> alias;

    public CommandProperties(String name, ArrayList<String> alias) {
        this.name = name;
        this.alias = alias;
    }

    public CommandProperties(String name) {
        this(name, new ArrayList<>());
    }

    public CommandProperties(String name, String... alias) {
        this(name, new ArrayList<>(Arrays.asList(alias)));
    }

    public static CommandProperties name(String name) {
        return new CommandProperties(name);
    }

    /**
     * 添加指令别名
     * 已弃用：名称不规范，使用 {@link #addAlias(String)}
     *
     * @param alias 指令别名
     * @return 自己的引用
     */
    @Deprecated
    public CommandProperties alias(String alias) {
        return addAlias(alias);
    }

    /**
     * 添加指令别名
     *
     * @param alias 指令别名
     * @return 自己的引用
     */
    public CommandProperties addAlias(String alias) {
        this.alias.add(alias);
        return this;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAlias() {
        return alias;
    }
}
