package com.demo.util;

import com.demo.tool.Clock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <h1>日期工具</h1>
 *
 * <h2>SimpleDateFormat格式化代码</h2>
 * <table>
 * <tr>
 * <td><b>标记
 * <td><b>解释
 * <tr>
 * <td>G
 * <td>公元前/后
 * <tr>
 * <td>yyyy
 * <td>年
 * <tr>
 * <td>MM
 * <td>月
 * <tr>
 * <td>dd
 * <td>日
 * <tr>
 * <td>hh
 * <td>12小时制
 * <tr>
 * <td>HH
 * <td>24小时制
 * <tr>
 * <td>mm
 * <td>分
 * <tr>
 * <td>ss
 * <td>秒
 * <tr>
 * <td>SSS
 * <td>毫秒
 * <tr>
 * <td>E
 * <td>星期
 * <tr>
 * <td>z
 * <td>时区
 * <tr>
 * <td>D
 * <td>一年中第几天
 * <tr>
 * <td>F
 * <td>当前月份内一周中的第几天
 * <tr>
 * <td>w
 * <td>一年中的第几周
 * <tr>
 * <td>W
 * <td>一月中的第几周
 * <tr>
 * <td>a
 * <td>AM/PM标记
 * <tr>
 * <td>k
 * <td>一天中的第几小时(1-24)
 * <tr>
 * <td>K
 * <td>AM/PM格式一天中的第几小时(0-11)
 * <tr>
 * <td>Y
 * <td>当天所在的周属于的年份，一周从周日开始，周六结束
 * <tr>
 * <td>L
 * <td>月(独立窗体)
 * <tr>
 * <td>u
 * <td>星期(1-7)
 * <tr>
 * <td>Z
 * <td>RFC822时区
 * <tr>
 * <td>X
 * <td>ISO8601时区
 * <tr>
 * <td>'
 * <td>文本定界符(a-zA-Z都被保留)
 * <tr>
 * <td>''
 * <td>单引号(双单引号)
 * </table>
 *
 * <h2>Calendar常量代码</h2>
 * <table>
 * <tr>
 * <td><b>字段
 * <td><b>解释
 * <tr>
 * <td>ERA
 * <td>世纪
 * <tr>
 * <td>YRAE
 * <td>年
 * <tr>
 * <td>MONTH
 * <td>月
 * <tr>
 * <td>DATE/DAY_OF_MONTH
 * <td>日
 * <tr>
 * <td>AM_PM
 * <td>上午/下午
 * <tr>
 * <td>HOUR
 * <td>12小时制
 * <tr>
 * <td>HOUR_OF_DAY
 * <td>24小时制
 * <tr>
 * <td>MINUTE
 * <td>分钟
 * <tr>
 * <td>SECOND
 * <td>秒
 * <tr>
 * <td>MILLISECOND
 * <td>毫秒
 * <tr>
 * <td>WEEK_OF_YEAR
 * <td>一年中的第几周
 * <tr>
 * <td>WEEK_OF_MONTH
 * <td>一月中的第几周
 * <tr>
 * <td>DAY_OF_YEAR
 * <td>一年中的第几天
 * <tr>
 * <td>DAY_OF_WEEK
 * <td>一年中的第几周
 * <tr>
 * <td>DAY_OF_WEEK_IN_MONTH
 * <td>当前月份内一周中的第几天
 * <tr>
 * <td>ZONE_OFFSET
 * <td>时区
 * <tr>
 * <td>DST_OFFSET
 * <td>夏令时
 * </table>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
public class DateUtils {

    /**
     * 1秒={@value}毫秒
     */
    public final static int MILLS_OF_SECOND = 1000;
    /**
     * 1分钟={@value}秒
     */
    public final static int SECOND_OF_MINUTE = 60;
    /**
     * 1小时={@value}分钟
     */
    public final static int MINUTE_OF_HOUR = 60;
    /**
     * 1天={@value}小时
     */
    public final static int HOUR_OF_DAY = 24;
    /**
     * 1月={@value}天(近似)
     */
    public final static int DAY_OF_MONTH = 30;
    /**
     * 1年={@value}天(近似)
     */
    public final static int DAY_OF_YEAR = 365;
    /**
     * 1年={@value}月
     */
    public final static int MONTH_OF_YEAR = 12;
    /**
     * 1分钟={@value}毫秒
     */
    public final static int MILLS_OF_MINUTE = MILLS_OF_SECOND * SECOND_OF_MINUTE;
    /**
     * 1小时={@value}毫秒
     */
    public final static int MILLS_OF_HOUR = MILLS_OF_MINUTE * MINUTE_OF_HOUR;
    /**
     * 1天={@value}毫秒
     */
    public final static int MILLS_OF_DAY = MILLS_OF_HOUR * HOUR_OF_DAY;
    /**
     * 1月={@value}毫秒(近似)
     *
     * @see #DAY_OF_MONTH
     */
    public final static long MILLS_OF_MONTH = (long) MILLS_OF_DAY * DAY_OF_MONTH;
    /**
     * 1年={@value}毫秒(近似)
     *
     * @see #DAY_OF_YEAR
     */
    public final static long MILLS_OF_YEAR = (long) MILLS_OF_DAY * DAY_OF_YEAR;
    /**
     * 1天的间隔={@value}毫秒
     *
     * @see #MILLS_OF_DAY
     */
    public final static long INTERVAL_DAY = MILLS_OF_DAY - 1;
    /**
     * 格式化：日期+时间{@value}
     */
    public final static String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化：日期{@value}
     */
    public final static String FORMAT_DATE = "yyyy-MM-dd";
    /**
     * 格式化：时间{@value}
     */
    public final static String FORMAT_TIME = "HH:mm:ss";

    public static void main(String[] args) {
        long timestamp = getTimestamp() + MILLS_OF_DAY + MILLS_OF_HOUR;
        System.out.println("timestamp");
        System.out.println("\t" + timestamp);

        System.out.println("getTimestamp()");
        System.out.println("\t" + getTimestamp());

        System.out.println("getSyncTimestamp()");
        System.out.println("\t" + getSyncTimestamp());

        System.out.println("getTimestamp(\"2020-09-08\", \"yyyy-MM-dd\")");
        System.out.println("\t" + getTimestamp("2020-09-08", "yyyy-MM-dd"));

        System.out.println("getTimestamp(\"2019-01-02 03:04:05\")");
        System.out.println("\t" + getTimestamp("2019-01-02 03:04:05"));

        System.out.println("getTimestamp(true, timestamp, Calendar.YEAR, 2)");
        System.out.println("\t" + getTimestamp(true, timestamp, Calendar.YEAR, 2));

        System.out.println("getStartTimestamp()");
        System.out.println("\t" + getStartTimestamp());

        System.out.println("getStartTimestamp(timestamp)");
        System.out.println("\t" + getStartTimestamp(timestamp));

        System.out.println("getStartTimestamp(10)");
        System.out.println("\t" + getStartTimestamp(10));

        System.out.println("getStartTimestamp(timestamp, 10)");
        System.out.println("\t" + getStartTimestamp(timestamp, 10));

        System.out.println("getEndTimestamp()");
        System.out.println("\t" + getEndTimestamp());

        System.out.println("getEndTimestamp(timestamp)");
        System.out.println("\t" + getEndTimestamp(timestamp));

        System.out.println("getEndTimestamp(10)");
        System.out.println("\t" + getEndTimestamp(10));

        System.out.println("getEndTimestamp(timestamp, 10)");
        System.out.println("\t" + getEndTimestamp(timestamp, 10));

        System.out.println("getDatetime(timestamp, \"yyyyMMdd_HHmmss.SSS\")");
        System.out.println("\t" + getDatetime(timestamp, "yyyyMMdd_HHmmss.SSS"));

        System.out.println("getDatetime(\"yyyyMMdd_HHmmss\")");
        System.out.println("\t" + getDatetime("yyyyMMdd_HHmmss"));

        System.out.println("getDatetime()");
        System.out.println("\t" + getDatetime());

        System.out.println("getDatetime(timestamp)");
        System.out.println("\t" + getDatetime(timestamp));

        System.out.println("getDate()");
        System.out.println("\t" + getDate());

        System.out.println("getDate(timestamp)");
        System.out.println("\t" + getDate(timestamp));

        System.out.println("getTime()");
        System.out.println("\t" + getTime());

        System.out.println("getTime(timestamp)");
        System.out.println("\t" + getTime(timestamp));

        // Runnable test1 = () -> {
        // for (int i = 0; i < 10; i++) {
        // System.out.println(Thread.currentThread().getName() + " : " +
        // getSyncTimestamp());
        // }
        // };
        // Runnable test2 = () -> {
        // for (int i = 0; i < 10; i++) {
        // System.out.println(Thread.currentThread().getName() + " : " +
        // getSyncTimestamp());
        // }
        // };
        // ThreadPool.execute(test1);
        // ThreadPool.execute(test2);
    }

    /**
     * 获取当前时间戳
     *
     * @see Clock#now()
     */
    public static long getTimestamp() {
        // 返回时间戳
        return Clock.now();
    }

    /**
     * 获取当前同步时间戳
     *
     * @return 同步操作失败返回-1
     * @see java.lang.Thread#sleep(long millis)
     */
    public synchronized static long getSyncTimestamp() {
        // 延迟1毫秒
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        // 返回时间戳
        return Clock.now();
    }

    /**
     * 获取指定日期的时间戳
     *
     * @param datetime 日期
     * @param format   日期格式
     * @return 日期转换失败返回-1
     * @see java.text.SimpleDateFormat#SimpleDateFormat(String pattern)
     * @see java.text.DateFormat#parse(String source)
     */
    public static long getTimestamp(String datetime, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date;
        // 转换
        try {
            date = dateFormat.parse(datetime);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        // 返回时间戳
        return date.getTime();
    }

    /**
     * 获取指定日期的时间戳
     *
     * @param datetime 日期yyyy-MM-dd HH:mm:ss格式
     * @return 日期转换失败返回-1
     * @see #getTimestamp(String datetime, String format)
     */
    public static long getTimestamp(String datetime) {
        return getTimestamp(datetime, FORMAT_DATETIME);
    }

    /**
     * 获取(当前/指定)时间戳的(指定偏移字段、偏移大小)的(开始/结束)时间戳
     *
     * @param isStart      开始/结束
     * @param timestamp    指定日期时间戳(-1为当前时间)
     * @param offsetField  偏移字段(offsetAmount为0时，此参数任意)
     * @param offsetAmount 偏移大小(0为不偏移)
     * @see java.util.Calendar
     */
    public static long getTimestamp(boolean isStart, long timestamp, int offsetField, int offsetAmount) {
        // 当前时间戳
        Calendar calendar = Calendar.getInstance();
        // 指定时间戳
        if (timestamp > -1) {
            calendar.setTimeInMillis(timestamp);
        }
        // 设置时分秒毫秒
        if (isStart) {
            // 开始时间
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        } else {
            // 结束时间
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
        }
        // 指定偏移
        if (offsetField > -1 && offsetAmount > 0) {
            calendar.add(offsetField, offsetAmount);
        }
        // 返回时间戳
        return calendar.getTimeInMillis();
    }

    /**
     * 获取今天0时0分0秒0毫秒的时间戳
     *
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int offsetAmount)
     */
    public static long getStartTimestamp() {
        return getTimestamp(true, -1, -1, 0);
    }

    /**
     * 获取指定时间戳0时0分0秒0毫秒的时间戳
     *
     * @param timestamp 指定时间戳
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int offsetAmount)
     */
    public static long getStartTimestamp(long timestamp) {
        return getTimestamp(true, timestamp, -1, 0);
    }

    /**
     * 获取今天+偏移天0时0分0秒0毫秒的时间戳
     *
     * @param dayOffset 相对于今天的偏移天
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int offsetAmount)
     */
    public static long getStartTimestamp(int dayOffset) {
        return getTimestamp(true, -1, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取指定时间戳+偏移天0时0分0秒0毫秒的时间戳
     *
     * @param timestamp 指定时间戳
     * @param dayOffset 相对于指定时间戳的偏移天
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int offsetAmount)
     */
    public static long getStartTimestamp(long timestamp, int dayOffset) {
        return getTimestamp(true, timestamp, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取今天23时59分59秒999毫秒的时间戳<br>
     * 如果已经调用过getStartXxx，请用变量保存并+INTERVAL_DAY来替代getEndXxx，这样速度更快
     *
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int offsetAmount)
     * @see #getStartTimestamp()
     * @see #INTERVAL_DAY
     */
    public static long getEndTimestamp() {
        return getTimestamp(false, -1, -1, 0);
    }

    /**
     * 获取指定时间戳23时59分59秒999毫秒的时间戳<br>
     * 如果已经调用过getStartXxx，请用变量保存并+INTERVAL_DAY来替代getEndXxx，这样速度更快
     *
     * @param timestamp 指定时间戳
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int offsetAmount)
     * @see #getStartTimestamp(long timestamp)
     * @see #INTERVAL_DAY
     */
    public static long getEndTimestamp(long timestamp) {
        return getTimestamp(false, timestamp, -1, 0);
    }

    /**
     * 获取今天+偏移天23时59分59秒999毫秒的时间戳<br>
     * 如果已经调用过getStartXxx，请用变量保存并+INTERVAL_DAY来替代getEndXxx，这样速度更快
     *
     * @param dayOffset 相对于今天的偏移天
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int offsetAmount)
     * @see #getStartTimestamp(int dayOffset)
     * @see #INTERVAL_DAY
     */
    public static long getEndTimestamp(int dayOffset) {
        return getTimestamp(false, -1, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取指定时间戳+偏移天23时59分59秒999毫秒的时间戳<br>
     * 如果已经调用过getStartXxx，请用变量保存并+INTERVAL_DAY来替代getEndXxx，这样速度更快
     *
     * @param timestamp 指定时间戳
     * @param dayOffset 相对于指定时间戳的偏移天
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int offsetAmount)
     * @see #getStartTimestamp(long timestamp, int dayOffset)
     * @see #INTERVAL_DAY
     */
    public static long getEndTimestamp(long timestamp, int dayOffset) {
        return getTimestamp(false, timestamp, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取(当前时间/指定时间戳)日期
     *
     * @param timestamp 时间戳(-1为当前时间)
     * @param format    日期格式
     * @see java.text.SimpleDateFormat#SimpleDateFormat(String pattern)
     * @see java.text.DateFormat#format(Date date)
     */
    public static String getDatetime(long timestamp, String format) {
        Date date;
        // 设置时间戳
        if (timestamp < 0) {
            // 当前时间戳
            date = new Date();
        } else {
            // 指定时间戳
            date = new Date(timestamp);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        // 返回转换后字符串
        return dateFormat.format(date);
    }

    /**
     * 获取当前日期
     *
     * @param format 日期格式
     * @see #getDatetime(long timestamp, String format)
     */
    public static String getDatetime(String format) {
        return getDatetime(-1, format);
    }

    /**
     * 获取当前日期的yyyy-MM-dd HH:mm:ss格式
     *
     * @see #getDatetime(long timestamp, String format)
     */
    public static String getDatetime() {
        return getDatetime(-1, FORMAT_DATETIME);
    }

    /**
     * 获取指定时间戳的日期yyyy-MM-dd HH:mm:ss格式
     *
     * @param timestamp 时间戳
     * @see #getDatetime(long timestamp, String format)
     */
    public static String getDatetime(long timestamp) {
        return getDatetime(timestamp, FORMAT_DATETIME);
    }

    /**
     * 获取当前日期的yyyy-MM-dd格式
     *
     * @see #getDatetime(long timestamp, String format)
     */
    public static String getDate() {
        return getDatetime(-1, FORMAT_DATE);
    }

    /**
     * 获取指定时间戳的yyyy-MM-dd格式
     *
     * @param timestamp 时间戳
     * @see #getDatetime(long timestamp, String format)
     */
    public static String getDate(long timestamp) {
        return getDatetime(timestamp, FORMAT_DATE);
    }

    /**
     * 获取当前日期的HH:mm:ss格式
     *
     * @see #getDatetime(long timestamp, String format)
     */
    public static String getTime() {
        return getDatetime(-1, FORMAT_TIME);
    }

    /**
     * 获取指定时间戳的HH:mm:ss格式
     *
     * @param timestamp 时间戳
     * @see #getDatetime(long timestamp, String format)
     */
    public static String getTime(long timestamp) {
        return getDatetime(timestamp, FORMAT_TIME);
    }
}
