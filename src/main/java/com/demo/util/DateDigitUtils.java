package com.demo.util;

import java.util.Calendar;

/**
 * <h1>数字日期工具</h1>
 *
 * <p>
 * 例如2020/11/13 15:40:26.123的数字日期是20201113154026123
 * </p>
 *
 * <p>
 * createDate 2020/11/13 15:40:26
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 */
public class DateDigitUtils {

    public static void main(String[] args) {
        long timestamp = getTimestamp() + 10000000000000L;
        System.out.println("timestamp");
        System.out.println("\t" + timestamp);

        long originTimestamp = getOriginTimestamp(timestamp);
        System.out.println("originTimestamp");
        System.out.println("\t" + originTimestamp);

        System.out.println("getOriginTimestamp(timestamp)");
        System.out.println("\t" + getOriginTimestamp(timestamp));

        System.out.println("getTimestamp(originTimestamp)");
        System.out.println("\t" + getTimestamp(originTimestamp));

        System.out.println("getTimestamp()");
        System.out.println("\t" + getTimestamp());

        System.out.println("getSyncTimestamp()");
        System.out.println("\t" + getSyncTimestamp());

        System.out.println("getDatetime()");
        System.out.println("\t" + getDatetime());

        System.out.println("getDate()");
        System.out.println("\t" + getDate());

        System.out.println("getTime()");
        System.out.println("\t" + getTime());

        System.out.println("getStartTimestamp()");
        System.out.println("\t" + getStartTimestamp());

        System.out.println("getStartTimestamp(timestamp)");
        System.out.println("\t" + getStartTimestamp(timestamp));

        System.out.println("getStartTimestampByOrigin()");
        System.out.println("\t" + getStartTimestampByOrigin(originTimestamp));

        System.out.println("getStartTimestamp(10)");
        System.out.println("\t" + getStartTimestamp(10));

        System.out.println("getStartTimestamp(timestamp, 10)");
        System.out.println("\t" + getStartTimestamp(timestamp, 10));

        System.out.println("getStartTimestampByOrigin(originTimestamp, 10)");
        System.out.println("\t" + getStartTimestampByOrigin(originTimestamp, 10));

        System.out.println("getEndTimestamp()");
        System.out.println("\t" + getEndTimestamp());

        System.out.println("getEndTimestamp(timestamp)");
        System.out.println("\t" + getEndTimestamp(timestamp));

        System.out.println("getEndTimestampByOrigin()");
        System.out.println("\t" + getEndTimestampByOrigin(originTimestamp));

        System.out.println("getEndTimestamp(10)");
        System.out.println("\t" + getEndTimestamp(10));

        System.out.println("getEndTimestamp(timestamp, 10)");
        System.out.println("\t" + getEndTimestamp(timestamp, 10));

        System.out.println("getEndTimestampByOrigin(originTimestamp, 10)");
        System.out.println("\t" + getEndTimestampByOrigin(originTimestamp, 10));

        System.out.println(complement(201));
        System.out.println(complement(2019));
        System.out.println(complement(201902));
        System.out.println(complement(20190203));
        System.out.println(complement(2019010203));
        System.out.println(complement(201901020304L));
        System.out.println(complement(20190102030405L));
        System.out.println(complement(20190102030405006L));
        System.out.println(complement(201901020304050067L));
        System.out.println(complement("201"));
        System.out.println(complement("2019"));
        System.out.println(complement("201902"));
        System.out.println(complement("20190203"));
        System.out.println(complement("2019010203"));
        System.out.println(complement("201901020304"));
        System.out.println(complement("20190102030405"));
        System.out.println(complement("20190102030405006"));
        System.out.println(complement("201901020304050067"));
    }

    /**
     * 1天的间隔={@value}单位
     */
    public final static long INTERVAL_DAY = 235959999;

    /**
     * 获取yyyyMMddHHmmssSSS形式时间戳的原始时间戳
     *
     * @param timestamp yyyyMMddHHmmssSSS形式时间戳
     * @see java.util.Calendar
     */
    public static long getOriginTimestamp(long timestamp) {
        // 当前原始时间戳
        Calendar calendar = Calendar.getInstance();
        // 设置年月日时分秒
        calendar.set((int) (timestamp / 10000000000000L), (int) (((timestamp / 100000000000L) % 100) - 1),
                (int) ((timestamp / 1000000000L) % 100), (int) ((timestamp / 10000000) % 100),
                (int) ((timestamp / 100000) % 100), (int) ((timestamp / 1000) % 100));
        // 设置毫秒
        calendar.set(Calendar.MILLISECOND, (int) (timestamp % 1000));
        // 返回原始时间戳
        return calendar.getTimeInMillis();
    }

    /**
     * 获取(当前/指定原始)时间戳yyyyMMddHHmmssSSS形式
     *
     * @param timestamp 原始时间戳(-1为当前时间)
     * @see java.util.Calendar
     */
    public static long getTimestamp(long timestamp) {
        // 当前原始时间戳
        Calendar calendar = Calendar.getInstance();
        // 指定原始时间戳
        if (timestamp > -1) {
            calendar.setTimeInMillis(timestamp);
        }
        // 返回时间戳
        return (calendar.get(Calendar.YEAR) * 10000000000000L) + ((calendar.get(Calendar.MONTH) + 1) * 100000000000L) + (calendar.get(Calendar.DAY_OF_MONTH) * 1000000000L) + (calendar.get(Calendar.HOUR_OF_DAY) * 10000000) + (calendar.get(Calendar.MINUTE) * 100000) + (calendar.get(Calendar.SECOND) * 1000) + (calendar.get(Calendar.MILLISECOND));
    }

    /**
     * 获取当前时间戳yyyyMMddHHmmssSSS形式
     *
     * @see #getTimestamp(long timestamp)
     */
    public static long getTimestamp() {
        return getTimestamp(-1);
    }

    /**
     * 获取当前同步时间戳yyyyMMddHHmmssSSS形式
     *
     * @return 同步操作失败返回-1
     * @see #getTimestamp(long timestamp)
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
        // 返回当前时间戳
        return getTimestamp(-1);
    }

    /**
     * 获取当前日期yyyyMMddHHmmss形式
     *
     * @see java.util.Calendar
     */
    public static long getDatetime() {
        // 获取原始时间戳
        Calendar calendar = Calendar.getInstance();
        // 返回当前日期yyyyMMddHHmmss形式
        return (calendar.get(Calendar.YEAR) * 10000000000L) + ((calendar.get(Calendar.MONTH) + 1) * 100000000L) + (calendar.get(Calendar.DAY_OF_MONTH) * 1000000) + (calendar.get(Calendar.HOUR_OF_DAY) * 10000) + (calendar.get(Calendar.MINUTE) * 100) + (calendar.get(Calendar.SECOND));
    }

    /**
     * 获取当前日期yyyyMMdd形式
     *
     * @see java.util.Calendar
     */
    public static int getDate() {
        // 获取原始时间戳
        Calendar calendar = Calendar.getInstance();
        // 返回当前日期yyyyMMdd形式
        return (calendar.get(Calendar.YEAR) * 10000) + ((calendar.get(Calendar.MONTH) + 1) * 100) + (calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 获取当前时间HHmmss形式
     *
     * @see java.util.Calendar
     */
    public static int getTime() {
        // 获取原始时间戳
        Calendar calendar = Calendar.getInstance();
        // 返回当前时间HHmmss形式
        return (calendar.get(Calendar.HOUR_OF_DAY) * 10000) + (calendar.get(Calendar.MINUTE) * 100) + (calendar.get(Calendar.SECOND));
    }

    /**
     * 获取(当前/指定)(原始)时间戳的(指定偏移字段、偏移大小)的(开始/结束)时间戳
     *
     * @param isStart      开始/结束
     * @param isOrigin     原始时间戳/时间戳(timestamp为-1时，此参数任意)
     * @param timestamp    指定日期时间戳(-1为当前时间)
     * @param offsetField  偏移字段(offsetAmount为0时，此参数任意)
     * @param offsetAmount 偏移大小(0为不偏移)
     * @see java.util.Calendar
     * @see #getOriginTimestamp(long timestamp)
     * @see #getTimestamp(long timestamp)
     */
    public static long getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int offsetField,
                                    int offsetAmount) {
        // 获取当前原始时间戳
        Calendar calendar = Calendar.getInstance();
        // 指定(原始)时间戳
        if (timestamp > -1) {
            if (isOrigin) {
                // 指定原始时间戳
                calendar.setTimeInMillis(timestamp);
            } else {
                // 指定时间戳，并转为原始时间戳
                // 设置年月日时分秒
                calendar.set((int) (timestamp / 10000000000000L), (int) (((timestamp / 100000000000L) % 100) - 1),
                        (int) ((timestamp / 1000000000L) % 100), (int) ((timestamp / 10000000) % 100),
                        (int) ((timestamp / 100000) % 100), (int) ((timestamp / 1000) % 100));
                // 设置毫秒
                calendar.set(Calendar.MILLISECOND, (int) (timestamp % 1000));
            }
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
        return (calendar.get(Calendar.YEAR) * 10000000000000L) + ((calendar.get(Calendar.MONTH) + 1) * 100000000000L) + (calendar.get(Calendar.DAY_OF_MONTH) * 1000000000L) + (calendar.get(Calendar.HOUR_OF_DAY) * 10000000) + (calendar.get(Calendar.MINUTE) * 100000) + (calendar.get(Calendar.SECOND) * 1000) + (calendar.get(Calendar.MILLISECOND));
    }

    /**
     * 获取今天0时0分0秒0毫秒的时间戳
     *
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     */
    public static long getStartTimestamp() {
        return getTimestamp(true, false, -1, -1, 0);
    }

    /**
     * 获取指定时间戳0时0分0秒0毫秒的时间戳
     *
     * @param timestamp 指定时间戳
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     */
    public static long getStartTimestamp(long timestamp) {
        return getTimestamp(true, false, timestamp, -1, 0);
    }

    /**
     * 获取指定原始时间戳0时0分0秒0毫秒的时间戳
     *
     * @param timestamp 指定原始时间戳
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     */
    public static long getStartTimestampByOrigin(long timestamp) {
        return getTimestamp(true, true, timestamp, -1, 0);
    }

    /**
     * 获取今天+偏移天0时0分0秒0毫秒的时间戳
     *
     * @param dayOffset 相对于今天的偏移天
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     */
    public static long getStartTimestamp(int dayOffset) {
        return getTimestamp(true, false, -1, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取指定时间戳+偏移天0时0分0秒0毫秒的时间戳
     *
     * @param timestamp 指定时间戳
     * @param dayOffset 相对于指定时间戳的偏移天
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     */
    public static long getStartTimestamp(long timestamp, int dayOffset) {
        return getTimestamp(true, false, timestamp, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取指定原始时间戳+偏移天0时0分0秒0毫秒的时间戳
     *
     * @param timestamp 指定原始时间戳
     * @param dayOffset 相对于指定时间戳的偏移天
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     */
    public static long getStartTimestampByOrigin(long timestamp, int dayOffset) {
        return getTimestamp(true, true, timestamp, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取今天23时59分59秒999毫秒的时间戳<br>
     * 如果已经调用过getStartXxx，请用变量保存并+INTERVAL_DAY来替代getEndXxx，这样速度更快
     *
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     * @see #getStartTimestamp()
     * @see #INTERVAL_DAY
     */
    public static long getEndTimestamp() {
        return getTimestamp(false, false, -1, -1, 0);
    }

    /**
     * 获取指定时间戳23时59分59秒999毫秒的时间戳<br>
     * 如果已经调用过getStartXxx，请用变量保存并+INTERVAL_DAY来替代getEndXxx，这样速度更快
     *
     * @param timestamp 指定时间戳
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     * @see #getStartTimestamp(long timestamp)
     * @see #INTERVAL_DAY
     */
    public static long getEndTimestamp(long timestamp) {
        return getTimestamp(false, false, timestamp, -1, 0);
    }

    /**
     * 获取指定原始时间戳23时59分59秒999毫秒的时间戳<br>
     * 如果已经调用过getStartXxx，请用变量保存并+INTERVAL_DAY来替代getEndXxx，这样速度更快
     *
     * @param timestamp 指定原始时间戳
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     * @see #getStartTimestampByOrigin(long timestamp)
     * @see #INTERVAL_DAY
     */
    public static long getEndTimestampByOrigin(long timestamp) {
        return getTimestamp(false, true, timestamp, -1, 0);
    }

    /**
     * 获取今天+偏移天23时59分59秒999毫秒的时间戳<br>
     * 如果已经调用过getStartXxx，请用变量保存并+INTERVAL_DAY来替代getEndXxx，这样速度更快
     *
     * @param dayOffset 相对于今天的偏移天
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     * @see #getStartTimestamp(int dayOffset)
     * @see #INTERVAL_DAY
     */
    public static long getEndTimestamp(int dayOffset) {
        return getTimestamp(false, false, -1, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取指定时间戳+偏移天23时59分59秒999毫秒的时间戳<br>
     * 如果已经调用过getStartXxx，请用变量保存并+INTERVAL_DAY来替代getEndXxx，这样速度更快
     *
     * @param timestamp 指定时间戳
     * @param dayOffset 相对于指定时间戳的偏移天
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     * @see #getStartTimestamp(long timestamp, int dayOffset)
     * @see #INTERVAL_DAY
     */
    public static long getEndTimestamp(long timestamp, int dayOffset) {
        return getTimestamp(false, false, timestamp, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 获取指定原始时间戳+偏移天23时59分59秒999毫秒的时间戳<br>
     * 如果已经调用过getStartXxx，请用变量保存并+INTERVAL_DAY来替代getEndXxx，这样速度更快
     *
     * @param timestamp 指定原始时间戳
     * @param dayOffset 相对于指定时间戳的偏移天
     * @see #getTimestamp(boolean isStart, boolean isOrigin, long timestamp, int
     * offsetField, int offsetAmount)
     * @see #getStartTimestampByOrigin(long timestamp, int dayOffset)
     * @see #INTERVAL_DAY
     */
    public static long getEndTimestampByOrigin(long timestamp, int dayOffset) {
        return getTimestamp(false, true, timestamp, Calendar.DAY_OF_YEAR, dayOffset);
    }

    /**
     * 补全缺损时间戳<br>
     * 需要保证长度为4/6/8/10/12/14/17位
     *
     * @param timestamp 缺损时间戳
     * @return 成功返回17位时间戳，失败返回-1
     * @see #complement(String timestamp)
     */
    public static long complement(long timestamp) {
        int len = 0;
        // 计算长度
        for (long temp = timestamp; temp > 0; len++) {
            temp /= 10;
        }
        if (len == 4) {
            // 年
            return timestamp * 10000000000000L + 101000000000L;
        } else if (len == 6) {
            // 年月
            return timestamp * 100000000000L + 1000000000L;
        } else if (len == 8) {
            // 年月日
            return timestamp * 1000000000;
        } else if (len == 10) {
            // 年月日时
            return timestamp * 10000000;
        } else if (len == 12) {
            // 年月日时分
            return timestamp * 100000;
        } else if (len == 14) {
            // 年月日时分秒
            return timestamp * 1000;
        } else if (len == 17) {
            // 年月日时分秒毫秒
            return timestamp;
        } else {
            // 错误
            return -1;
        }
    }

    /**
     * 补全缺损时间戳<br>
     * 需要保证长度为4/6/8/10/12/14/17位
     *
     * @param timestamp 缺损时间戳
     * @return 成功返回17位时间戳，失败返回-1
     * @see #complement(long timestamp)
     */
    public static String complement(String timestamp) {
        int len = timestamp.length();
        if (len == 4) {
            // 年
            return timestamp + "0101000000000";
        } else if (len == 6) {
            // 年月
            return timestamp + "01000000000";
        } else if (len == 8) {
            // 年月日
            return timestamp + "000000000";
        } else if (len == 10) {
            // 年月日时
            return timestamp + "0000000";
        } else if (len == 12) {
            // 年月日时分
            return timestamp + "00000";
        } else if (len == 14) {
            // 年月日时分秒
            return timestamp + "000";
        } else if (len == 17) {
            // 年月日时分秒毫秒
            return timestamp;
        } else {
            // 错误
            return "-1";
        }
    }

}
