package com.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * <h1>定制日志工具</h1>
 *
 * <p>createDate 2020/11/11 11:11:11</p>
 *
 * @author ALI[1416978277@qq.com]
 */
public class L {
    private static Logger Log = LoggerFactory.getLogger("Log");

    public static void t(String tag, String msg) {
        String[] s = getTrace();
        Log = LoggerFactory.getLogger(tag);
        Log.trace(s[1] + msg);
    }

    public static void d(String tag, String msg) {
        String[] s = getTrace();
        Log = LoggerFactory.getLogger(tag);
        Log.debug(s[1] + msg);
    }

    public static void i(String tag, String msg) {
        String[] s = getTrace();
        Log = LoggerFactory.getLogger(tag);
        Log.info(s[1] + msg);
    }

    public static void w(String tag, String msg) {
        String[] s = getTrace();
        Log = LoggerFactory.getLogger(tag);
        Log.warn(s[1] + msg);
    }

    public static void e(String tag, String msg) {
        String[] s = getTrace();
        Log = LoggerFactory.getLogger(tag);
        Log.error(s[1] + msg);
    }

    public static void t(String msg) {
        String[] s = getTrace();
        Log = LoggerFactory.getLogger(s[0]);
        Log.trace(s[1] + msg);
    }

    public static void d(String msg) {
        String[] s = getTrace();
        Log = LoggerFactory.getLogger(s[0]);
        Log.debug(s[1] + msg);
    }

    public static void i(String msg) {
        String[] s = getTrace();
        Log = LoggerFactory.getLogger(s[0]);
        Log.info(s[1] + msg);
    }

    public static void w(String msg) {
        String[] s = getTrace();
        Log = LoggerFactory.getLogger(s[0]);
        Log.warn(s[1] + msg);
    }

    public static void e(String msg) {
        String[] s = getTrace();
        Log = LoggerFactory.getLogger(s[0]);
        Log.error(s[1] + msg);
    }

    private static String[] getTrace() {
        StackTraceElement[] traces = new Throwable().fillInStackTrace().getStackTrace();
        String[] s = new String[2];
        for (int i = 2; i < traces.length; i++) {
            String className = traces[i].getClassName();
            if (!className.equals(L.class.getName())) {
                s[0] = traces[i].getClassName();
                s[1] = String.format(Locale.US, "%s(%s:%s) ", traces[i].getMethodName(), traces[i].getFileName(), traces[i].getLineNumber());
                return s;
            }
        }
        s[0] = "LOG_ERROR";
        s[1] = String.format(Locale.US, "%s.%s(%s:%s) ", traces[0].getClassName(), traces[0].getMethodName(), traces[0].getFileName(), traces[0].getLineNumber());
        return s;
    }
}