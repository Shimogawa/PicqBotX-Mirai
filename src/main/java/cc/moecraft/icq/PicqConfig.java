package cc.moecraft.icq;

import cc.moecraft.logger.environments.ColorSupportLevel;

import java.util.function.Consumer;

public class PicqConfig {
    private static final PicqConfig instance = new PicqConfig();

    public static PicqConfig getInstance() {
        return instance;
    }

    public static PicqConfig modifyConfig(Consumer<PicqConfig> block) {
        block.accept(instance);
        return instance;
    }

    private boolean isDebug = false;

    private boolean isMaintenanceMode = false;

    private String maintenanceResponse = "";

    private String logPath = "logs";

    private String logFileName = "PicqBotX-Log";

    private ColorSupportLevel colorSupportLevel = ColorSupportLevel.OS_DEPENDENT;

    private boolean commandsAlsoCallEvents = false;

    private boolean useAsyncCommands = false;

    private String commandArgsSplitRegex = " ";

    private Consumer<Throwable> commandErrorHandler = Throwable::printStackTrace;

    private String deviceInfoFile = "deviceInfo.json";

    /**
     * 单位：秒
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
