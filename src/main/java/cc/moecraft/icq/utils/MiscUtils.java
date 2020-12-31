package cc.moecraft.icq.utils;

import cc.moecraft.logger.HyLogger;
import cc.moecraft.utils.cli.ResourceUtils;

import static cc.moecraft.logger.format.AnsiColor.*;
import static cc.moecraft.logger.format.AnsiFormat.replaceAllFormatWithANSI;
import static cc.moecraft.utils.StringUtils.repeat;

public class MiscUtils {
    /**
     * 输出一行"初始化完成"的日志
     *
     * @param logger 日志对象
     * @param name 要输出的名字
     * @param greens 绿色星号数量
     * @param reds 红色星号数量
     */
    public static void logInitDone(HyLogger logger, String name, int greens, int reds)
    {
        String green = repeat("*", greens);
        String red = repeat("*", reds);

        logger.log(String.format("%s%s%s初始化完成%s [%s%s%s%s%s] ...(%s ms)",
            YELLOW, name, GREEN, YELLOW,
            GREEN, green, RED, red, YELLOW,
            Math.round(logger.timing.getMilliseconds() * 100.0) / 100.0));

        logger.timing.reset();
    }

    /**
     * 日志一个资源
     *
     * @param logger Logger
     * @param name   资源名
     * @param vars   变量
     */
    public static void logResource(HyLogger logger, String name, Object... vars) {
        ResourceUtils.printResource(
            MiscUtils.class.getClassLoader(),
            s -> logger.log(replaceAllFormatWithANSI(s)),
            name,
            vars
        );
    }
}
