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
     * 是否使用异步指令执行
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
     * 执行垃圾回收与清理消息模板字典的时间间隔。单位：秒
     */
    private long scheduledClearWeakRefTimeInterval = 60 * 60L;

    private PicqConfig() {
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public boolean isMaintenanceMode() {
        return isMaintenanceMode;
    }

    public PicqConfig setMaintenanceMode(boolean maintenanceMode) {
        isMaintenanceMode = maintenanceMode;
        return this;
    }

    public String getMaintenanceResponse() {
        return maintenanceResponse;
    }

    public PicqConfig setMaintenanceResponse(String maintenanceResponse) {
        this.maintenanceResponse = maintenanceResponse;
        return this;
    }

    public String getLogPath() {
        return logPath;
    }

    public PicqConfig setLogPath(String logPath) {
        this.logPath = logPath;
        return this;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public PicqConfig setLogFileName(String logFileName) {
        this.logFileName = logFileName;
        return this;
    }

    public ColorSupportLevel getColorSupportLevel() {
        return colorSupportLevel;
    }

    public PicqConfig setColorSupportLevel(ColorSupportLevel colorSupportLevel) {
        this.colorSupportLevel = colorSupportLevel;
        return this;
    }

    public boolean isCommandsAlsoCallEvents() {
        return commandsAlsoCallEvents;
    }

    public PicqConfig setCommandsAlsoCallEvents(boolean commandsAlsoCallEvents) {
        this.commandsAlsoCallEvents = commandsAlsoCallEvents;
        return this;
    }

    public boolean isUseAsyncCommands() {
        return useAsyncCommands;
    }

    public PicqConfig setUseAsyncCommands(boolean useAsyncCommands) {
        this.useAsyncCommands = useAsyncCommands;
        return this;
    }

    public String getCommandArgsSplitRegex() {
        return commandArgsSplitRegex;
    }

    public PicqConfig setCommandArgsSplitRegex(String commandArgsSplitRegex) {
        this.commandArgsSplitRegex = commandArgsSplitRegex;
        return this;
    }

    public Consumer<Throwable> getCommandErrorHandler() {
        return commandErrorHandler;
    }

    public PicqConfig setCommandErrorHandler(Consumer<Throwable> commandErrorHandler) {
        this.commandErrorHandler = commandErrorHandler;
        return this;
    }

    public String getDeviceInfoFile() {
        return deviceInfoFile;
    }

    public PicqConfig setDeviceInfoFile(String deviceInfoFile) {
        this.deviceInfoFile = deviceInfoFile;
        return this;
    }

    public long getScheduledClearWeakRefTimeInterval() {
        return scheduledClearWeakRefTimeInterval;
    }

    public PicqConfig setScheduledClearWeakRefTimeInterval(long scheduledClearWeakRefTimeInterval) {
        this.scheduledClearWeakRefTimeInterval = scheduledClearWeakRefTimeInterval;
        return this;
    }
}
