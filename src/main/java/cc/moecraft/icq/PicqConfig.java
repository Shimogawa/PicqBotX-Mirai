package cc.moecraft.icq;

import java.util.function.Consumer;

public class PicqConfig {
    private boolean isDebug = false;

    private String logPath = "logs";

    private String logFileName = "PicqBotX-Log";

    private boolean commandsAlsoCallEvents = true;

    private Consumer<Throwable> commandErrorHandler = Throwable::printStackTrace;

    private String deviceInfoFile = "deviceInfo.json";

    /**
     * 单位：秒
     */
    private long scheduledClearWeakRefTimeInterval = 60 * 60L;

    public PicqConfig() {
    }

    public PicqConfig(Consumer<PicqConfig> block) {
        block.accept(this);
    }

    public static PicqConfig getDefault() {
        return new PicqConfig();
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
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

    public boolean isCommandsAlsoCallEvents() {
        return commandsAlsoCallEvents;
    }

    public PicqConfig setCommandsAlsoCallEvents(boolean commandsAlsoCallEvents) {
        this.commandsAlsoCallEvents = commandsAlsoCallEvents;
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
