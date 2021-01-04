package cc.moecraft.icq;

import cc.moecraft.logger.environments.ColorSupportLevel;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * 机器人配置
 */
public class PicqConfig {
    private static final PicqConfig instance = new PicqConfig();
    /**
     * 是否输出 debug 消息
     */
    private boolean isDebug = false;

    /**
     * 是否启用维护模式
     */
    private boolean isMaintenanceMode = false;

    /**
     * 维护模式回复的消息
     */
    private String maintenanceResponse = "";

    /**
     * Logger 日志路径 (设为空就不输出文件了)
     */
    private String logPath = "logs";

    /**
     * Logger 日志文件名
     */
    private String logFileName = "PicqBotX-Log";

    /**
     * Logger 颜色支持级别
     */
    private ColorSupportLevel colorSupportLevel = ColorSupportLevel.OS_DEPENDENT;

    /**
     * 指令是否触发消息事件
     */
    private boolean commandsAlsoCallEvents = false;

    /**
     * 是否使用异步执行指令
     */
    private boolean useAsyncCommands = false;

    /**
     * 解析指令的时候用来分割参数的正则
     */
    private String commandArgsSplitRegex = " ";

    /**
     * 执行指令时抛出异常处理（可以通过把 {@code commandErrorHandler} 设置为 {@code (ex)->{}} 来关闭异常抛出）
     */
    private Consumer<Throwable> commandErrorHandler = Throwable::printStackTrace;

    /**
     * Mirai 的设备信息储存位置。为 {@code null} 或空字符串则使用默认值（{@code device.json}）。
     */
    private @Nullable String deviceInfoFile = null;

    /**
     * 默认是否使用消息模板发送消息
     */
    private boolean defaultSendUseMessageTemplate = true;

    /**
     * 执行垃圾回收与清理消息模板字典的时间间隔。单位：秒
     */
    private long scheduledClearWeakRefTimeInterval = 60 * 60L;

    private PicqConfig() {
    }

    /**
     * @return 配置的单例对象
     */
    public static PicqConfig getInstance() {
        return instance;
    }

    /**
     * 修改配置
     *
     * @param block 修改配置的函数
     * @return 自身的引用
     */
    public static PicqConfig modifyConfig(Consumer<PicqConfig> block) {
        block.accept(instance);
        return instance;
    }

    /**
     * 获取是否输出 debug 消息
     *
     * @return 是否输出 debug 消息
     */
    public boolean isDebug() {
        return isDebug;
    }

    /**
     * 设置是否输出 debug 消息
     *
     * @param debug 是否输出 debug 消息
     * @return 自身的引用
     */
    public PicqConfig setDebug(boolean debug) {
        isDebug = debug;
        return this;
    }

    /**
     * 获取是否启用维护模式
     *
     * @return 是否启用维护模式
     */
    public boolean isMaintenanceMode() {
        return isMaintenanceMode;
    }

    /**
     * 设置是否启用维护模式
     *
     * @param maintenanceMode 是否启用维护模式
     * @return 自身的引用
     */
    public PicqConfig setMaintenanceMode(boolean maintenanceMode) {
        isMaintenanceMode = maintenanceMode;
        return this;
    }

    /**
     * 获取维护模式回复的消息
     *
     * @return 维护模式回复的消息
     */
    public String getMaintenanceResponse() {
        return maintenanceResponse;
    }

    /**
     * 设置维护模式回复的消息
     *
     * @param maintenanceResponse 维护模式回复的消息
     * @return 自身的引用
     */
    public PicqConfig setMaintenanceResponse(String maintenanceResponse) {
        this.maintenanceResponse = maintenanceResponse;
        return this;
    }

    /**
     * 获取日志路径 (为空就不输出文件了)
     *
     * @return 日志路径 (为空就不输出文件了)
     */
    public String getLogPath() {
        return logPath;
    }

    /**
     * 设置日志路径 (设为空就不输出文件了)
     *
     * @param logPath 日志路径
     * @return 自身的引用
     */
    public PicqConfig setLogPath(String logPath) {
        this.logPath = logPath;
        return this;
    }

    /**
     * 获取日志文件名
     *
     * @return 日志文件名
     */
    public String getLogFileName() {
        return logFileName;
    }

    /**
     * 设置日志文件名
     *
     * @param logFileName 日志文件名
     * @return 自身的引用
     */
    public PicqConfig setLogFileName(String logFileName) {
        this.logFileName = logFileName;
        return this;
    }

    /**
     * 获取日志颜色支持级别
     *
     * @return 日志颜色支持级别
     */
    public ColorSupportLevel getColorSupportLevel() {
        return colorSupportLevel;
    }

    /**
     * 设置日志颜色支持级别
     *
     * @param colorSupportLevel 日志颜色支持级别
     * @return 自身的引用
     */
    public PicqConfig setColorSupportLevel(ColorSupportLevel colorSupportLevel) {
        this.colorSupportLevel = colorSupportLevel;
        return this;
    }

    /**
     * 获取指令是否触发消息事件
     *
     * @return 指令是否触发消息事件
     */
    public boolean isCommandsAlsoCallEvents() {
        return commandsAlsoCallEvents;
    }

    /**
     * 设置指令是否触发消息事件
     *
     * @param commandsAlsoCallEvents 指令是否触发消息事件
     * @return 自身的引用
     */
    public PicqConfig setCommandsAlsoCallEvents(boolean commandsAlsoCallEvents) {
        this.commandsAlsoCallEvents = commandsAlsoCallEvents;
        return this;
    }

    /**
     * 获取是否使用异步执行指令
     *
     * @return 是否使用异步执行指令
     */
    public boolean isUseAsyncCommands() {
        return useAsyncCommands;
    }

    /**
     * 设置是否使用异步执行指令
     *
     * @param useAsyncCommands 是否使用异步执行指令
     * @return 自身的引用
     */
    public PicqConfig setUseAsyncCommands(boolean useAsyncCommands) {
        this.useAsyncCommands = useAsyncCommands;
        return this;
    }

    /**
     * 获取解析指令的时候用来分割参数的正则
     *
     * @return 解析指令的时候用来分割参数的正则
     */
    public String getCommandArgsSplitRegex() {
        return commandArgsSplitRegex;
    }

    /**
     * 设置解析指令的时候用来分割参数的正则
     *
     * @param commandArgsSplitRegex 解析指令的时候用来分割参数的正则
     * @return 自身的引用
     */
    public PicqConfig setCommandArgsSplitRegex(String commandArgsSplitRegex) {
        this.commandArgsSplitRegex = commandArgsSplitRegex;
        return this;
    }

    /**
     * 获取执行指令时抛出异常处理函数
     *
     * @return 执行指令时抛出异常处理函数
     */
    public Consumer<Throwable> getCommandErrorHandler() {
        return commandErrorHandler;
    }

    /**
     * 设置执行指令时抛出异常处理函数（可以通过把 {@code commandErrorHandler} 设置为 {@code (ex)->{}} 来关闭异常抛出）
     *
     * @param commandErrorHandler 执行指令时抛出异常处理函数
     * @return 自身的引用
     */
    public PicqConfig setCommandErrorHandler(Consumer<Throwable> commandErrorHandler) {
        this.commandErrorHandler = commandErrorHandler;
        return this;
    }

    /**
     * 获取默认是否使用消息模板发送消息
     *
     * @return 默认是否使用消息模板发送消息
     */
    public boolean isDefaultSendUseMessageTemplate() {
        return defaultSendUseMessageTemplate;
    }

    /**
     * 设置默认是否使用消息模板发送消息
     *
     * @param defaultSendUseMessageTemplate 默认是否使用消息模板发送消息
     * @return 自身的引用
     */
    public PicqConfig setDefaultSendUseMessageTemplate(boolean defaultSendUseMessageTemplate) {
        this.defaultSendUseMessageTemplate = defaultSendUseMessageTemplate;
        return this;
    }

    /**
     * 获取Mirai 的设备信息储存位置。为 {@code null} 或空字符串则使用 Mirai 的默认值（{@code device.json}）。
     *
     * @return Mirai 的设备信息储存位置
     */
    public @Nullable String getDeviceInfoFile() {
        return deviceInfoFile;
    }

    /**
     * 设置 Mirai 的设备信息储存位置。为 {@code null} 或空字符串则使用 Mirai 的默认值（{@code device.json}）。
     *
     * @param deviceInfoFile Mirai 的设备信息储存位置
     * @return 自身的引用
     */
    public PicqConfig setDeviceInfoFile(String deviceInfoFile) {
        this.deviceInfoFile = deviceInfoFile;
        return this;
    }

    /**
     * 获取执行垃圾回收与清理消息模板字典的时间间隔。单位：秒
     *
     * @return 执行垃圾回收与清理消息模板字典的时间间隔。单位：秒
     */
    public long getScheduledClearWeakRefTimeInterval() {
        return scheduledClearWeakRefTimeInterval;
    }

    /**
     * 设置执行垃圾回收与清理消息模板字典的时间间隔。单位：秒
     *
     * @param scheduledClearWeakRefTimeInterval 执行垃圾回收与清理消息模板字典的时间间隔。单位：秒
     * @return 自身的引用
     */
    public PicqConfig setScheduledClearWeakRefTimeInterval(long scheduledClearWeakRefTimeInterval) {
        this.scheduledClearWeakRefTimeInterval = scheduledClearWeakRefTimeInterval;
        return this;
    }
}
