package com.demo.util;

import java.util.Calendar;

/**
 * <h1>数字日期工具类</h1>
 * 
 * <p>
 * 例如2020/11/13 15:40:26.123的数字日期是20201113154026123<br>
 * </p>
 *
 * <p>
 * createDate 2020/11/13 15:40:26
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 */
public class DateDigitUtils {
    public static void main(String[] args) {
        System.out.println(getSyncTimestamp());
        System.out.println(getTimestamp());
        System.out.println(getDatetime());
        System.out.println(getDate());
        System.out.println(getTime());
//        Runnable test1 = () -> {
//            for (int i = 0; i < 10; i++) {
//                System.out.println(Thread.currentThread().getName() + " : " + getTimestamp());
//            }
//        };
//        Runnable test2 = () -> {
//            for (int i = 0; i < 10; i++) {
//                System.out.println(Thread.currentThread().getName() + " : " + getTimestamp());
//            }
//        };
//        ThreadPool.execute(test1);
//        ThreadPool.execute(test2);
    }

    public static int addDay(int date, int day) {
        Calendar d = Calendar.getInstance();
        d.set(date / 10000, ((date / 100) % 100 - 1), date % 100);
        d.add(Calendar.DAY_OF_YEAR, day);
        return d.get(Calendar.YEAR) * 10000 + (d.get(Calendar.MONTH) + 1) * 100 + d.get(Calendar.DATE);
    }

    /**
     * 获取(当前/指定原始)时间戳yyyyMMddHHmmssSSS形式<br>
     *
     * @param timestamp 原始时间戳(-1为当前时间)
     * @see java.util.Calendar
     */
    public static long getTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        if (timestamp != -1) {
            calendar.setTimeInMillis(timestamp);
        }
        return calendar.get(Calendar.YEAR) * 10000000000000L + (calendar.get(Calendar.MONTH) + 1) * 100000000000L
                + calendar.get(Calendar.DAY_OF_MONTH) * 1000000000L + calendar.get(Calendar.HOUR_OF_DAY) * 10000000
                + calendar.get(Calendar.MINUTE) * 100000 + calendar.get(Calendar.SECOND) * 1000
                + calendar.get(Calendar.MILLISECOND);
    }

    /**
     * 获取当前时间戳yyyyMMddHHmmssSSS形式<br>
     * 
     * @see #getTimestamp(long)
     */
    public static long getTimestamp() {
        return getTimestamp(-1);
    }

    /**
     * 获取当前同步时间戳yyyyMMddHHmmssSSS形式
     *
     * @return 同步操作失败返回-1
     * 
     * @see #getTimestamp(long)
     * @see java.lang.Thread#sleep(long millis)
     */
    public synchronized static long getSyncTimestamp() {
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return getTimestamp(-1);
    }

    /**
     * 获取当前日期yyyyMMddHHmmss形式
     */
    public static long getDatetime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) * 10000000000L + (calendar.get(Calendar.MONTH) + 1) * 100000000L
                + calendar.get(Calendar.DAY_OF_MONTH) * 1000000 + calendar.get(Calendar.HOUR_OF_DAY) * 10000
                + calendar.get(Calendar.MINUTE) * 100 + calendar.get(Calendar.SECOND);
    }

    /**
     * 获取当前日期yyyyMMdd形式
     */
    public static int getDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100
                + calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间HHmmss形式
     */
    public static int getTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY) * 10000 + calendar.get(Calendar.MINUTE) * 100
                + calendar.get(Calendar.SECOND);
    }

    /**
     * 获取(当前/指定)时间戳的(指定偏移字段、偏移大小)的(开始/结束)时间戳
     *
     * @param isStart      开始/结束
     * @param timestamp    指定日期时间戳(-1为当前时间)
     * @param offsetField  偏移字段(-1为不偏移)
     * @param offsetAmount 偏移大小(0为不偏移)
     * @see java.util.Calendar
     */
    public static long getTimestamp(boolean isStart, long timestamp, int offsetField, int offsetAmount) {
        Calendar calendar = Calendar.getInstance();
        if (timestamp != -1) {
            calendar.setTimeInMillis(timestamp);
        }
        if (isStart) {
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    0, 0, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        } else {
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    23, 59, 59);
            calendar.set(Calendar.MILLISECOND, 999);
        }
        if (offsetField != -1 && offsetAmount != 0) {
            calendar.add(offsetField, offsetAmount);
        }
        return calendar.getTime().getTime();
    }

    /**
     * 获取今天0时0分0秒0毫秒的时间戳
     *
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int
     *      offsetAmount)
     */
    public static long getStartTimestamp() {
        return getTimestamp(true, -1, -1, 0);
    }

    /**
     * 获取指定时间戳0时0分0秒0毫秒的时间戳
     *
     * @param timestamp 指定时间戳
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int
     *      offsetAmount)
     */
    public static long getStartTimestamp(long timestamp) {
        return getTimestamp(true, timestamp, -1, 0);
    }

    /**
     * 获取今天+偏移天0时0分0秒0毫秒的时间戳
     *
     * @param dayOffset 相对于今天的偏移天
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int
     *      offsetAmount)
     */
    public static long getStartTimestamp(int dayOffset) {
        return getTimestamp(true, -1, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取指定时间戳+偏移天0时0分0秒0毫秒的时间戳
     *
     * @param timestamp 指定时间戳
     * @param dayOffset 相对于指定时间戳的偏移天
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int
     *      offsetAmount)
     */
    public static long getStartTimestamp(long timestamp, int dayOffset) {
        return getTimestamp(true, timestamp, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取今天23时59分59秒999毫秒的时间戳
     *
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int
     *      offsetAmount)
     */
    public static long getEndTimestamp() {
        return getTimestamp(false, -1, -1, 0);
    }

    /**
     * 获取指定时间戳23时59分59秒999毫秒的时间戳
     *
     * @param timestamp 指定时间戳
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int
     *      offsetAmount)
     */
    public static long getEndTimestamp(long timestamp) {
        return getTimestamp(false, timestamp, -1, 0);
    }

    /**
     * 获取今天+偏移天23时59分59秒999毫秒的时间戳
     *
     * @param dayOffset 相对于今天的偏移天
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int
     *      offsetAmount)
     */
    public static long getEndTimestamp(int dayOffset) {
        return getTimestamp(false, -1, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取指定时间戳+偏移天23时59分59秒999毫秒的时间戳
     *
     * @param timestamp 指定时间戳
     * @param dayOffset 相对于指定时间戳的偏移天
     * @see #getTimestamp(boolean isStart, long timestamp, int offsetField, int
     *      offsetAmount)
     */
    public static long getEndTimestamp(long timestamp, int dayOffset) {
        return getTimestamp(false, timestamp, Calendar.DAY_OF_YEAR, dayOffset);
    }

}
