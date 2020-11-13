package com.demo.tool;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>日志</h1>
 *
 * <p>
 * 配置文件logging-level-root设置打印的日志等级<br>
 * logging-file-name设置日志文件输出路径和文件名
 * </p>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 */
public class Log {

    /**
     * 日志实例
     */
    private static Logger logger = LoggerFactory.getLogger("Log");

    /**
     * trace
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void t(String tag, String msg) {
        String[] s = getTrace();
        logger = LoggerFactory.getLogger(tag);
        logger.trace(s[1] + msg);
    }

    /**
     * debug
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void d(String tag, String msg) {
        String[] s = getTrace();
        logger = LoggerFactory.getLogger(tag);
        logger.debug(s[1] + msg);
    }

    /**
     * info
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void i(String tag, String msg) {
        String[] s = getTrace();
        logger = LoggerFactory.getLogger(tag);
        logger.info(s[1] + msg);
    }

    /**
     * warning
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void w(String tag, String msg) {
        String[] s = getTrace();
        logger = LoggerFactory.getLogger(tag);
        logger.warn(s[1] + msg);
    }

    /**
     * error
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void e(String tag, String msg) {
        String[] s = getTrace();
        logger = LoggerFactory.getLogger(tag);
        logger.error(s[1] + msg);
    }

    /**
     * trace
     *
     * @param msg 信息
     */
    public static void t(String msg) {
        String[] s = getTrace();
        logger = LoggerFactory.getLogger(s[0]);
        logger.trace(s[1] + msg);
    }

    /**
     * debug
     *
     * @param msg 信息
     */
    public static void d(String msg) {
        String[] s = getTrace();
        logger = LoggerFactory.getLogger(s[0]);
        logger.debug(s[1] + msg);
    }

    /**
     * info
     *
     * @param msg 信息
     */
    public static void i(String msg) {
        String[] s = getTrace();
        logger = LoggerFactory.getLogger(s[0]);
        logger.info(s[1] + msg);
    }

    /**
     * warning
     *
     * @param msg 信息
     */
    public static void w(String msg) {
        String[] s = getTrace();
        logger = LoggerFactory.getLogger(s[0]);
        logger.warn(s[1] + msg);
    }

    /**
     * error
     *
     * @param msg 信息
     */
    public static void e(String msg) {
        String[] s = getTrace();
        logger = LoggerFactory.getLogger(s[0]);
        logger.error(s[1] + msg);
    }

    /**
     * 获取打印日志的文件信息
     */
    private static String[] getTrace() {
        StackTraceElement[] traces = new Throwable().fillInStackTrace().getStackTrace();
        String[] s = new String[2];
        for (int i = 2; i < traces.length; i++) {
            String className = traces[i].getClassName();
            if (!className.equals(Log.class.getName())) {
                s[0] = traces[i].getClassName();
                s[1] = String.format(Locale.US, ".%s(%s:%s) ", traces[i].getMethodName(), traces[i].getFileName(), traces[i].getLineNumber());
                return s;
            }
        }
        s[0] = "LOG_ERROR";
        s[1] = String.format(Locale.US, "%s.%s(%s:%s) ", traces[0].getClassName(), traces[0].getMethodName(), traces[0].getFileName(), traces[0].getLineNumber());
        return s;
    }
}