package com.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <h1>数字型日期工具类</h1>
 * 
 * @author ALI(1416978277@qq.com)
 * 
 * @create 2020年11月11日11:11:11
 * @update 2020年11月11日11:11:11
 *
 */
public class DateDigitUtils {
	/**
	 * int型日期计算
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static int addDay(int date, int day) {
		Calendar d = Calendar.getInstance();
		d.set(date / 10000, ((date / 100) % 100 - 1), date % 100);
		d.add(Calendar.DAY_OF_YEAR, day);
		return d.get(Calendar.YEAR) * 10000 + (d.get(Calendar.MONTH) + 1) * 100 + d.get(Calendar.DATE);
	}

	/**
	 * 获得现在的日期
	 * 
	 * @return
	 */
	public static int getDate1() {
		Calendar d = Calendar.getInstance();
		return d.get(Calendar.YEAR) * 10000 + (d.get(Calendar.MONTH) + 1) * 100 + d.get(Calendar.DATE);
	}

	public static void main(String[] args) {
		long timestamp = 1602232123456L;
		System.out.println("getDate()");
		System.out.println("\t" + getDate());
		System.out.println("getDate(timestamp)");
		System.out.println("\t" + getDate(timestamp));
		System.out.println("getDatetime()");
		System.out.println("\t" + getDatetime());
		System.out.println("getDatetime(\"yyyyMMdd_hhmmss\")");
		System.out.println("\t" + getDatetime("yyyyMMdd_hhmmss"));
		System.out.println("getDatetime(timestamp, \"yyyyMMdd_hhmmss\")");
		System.out.println("\t" + getDatetime(timestamp, "yyyyMMdd_hhmmss"));
		System.out.println("getSyncTimestamp()");
		System.out.println("\t" + getSyncTimestamp());
		System.out.println("getTime()");
		System.out.println("\t" + getTime());
		System.out.println("getTimestamp()");
		System.out.println("\t" + getTimestamp());
		System.out.println("getTodayEndTimestamp()");
		System.out.println("\t" + getTodayEndTimestamp());
		System.out.println("getTodayEndTimestamp(10)");
		System.out.println("\t" + getTodayEndTimestamp(10));
		System.out.println("getTodayEndTimestamp(timestamp)");
		System.out.println("\t" + getTodayEndTimestamp(timestamp));
		System.out.println("getTodayEndTimestamp(10, timestamp)");
		System.out.println("\t" + getTodayEndTimestamp(10, timestamp));
		System.out.println("getTodayTimestamp()");
		System.out.println("\t" + getTodayTimestamp());
		System.out.println("getTodayTimestamp(10)");
		System.out.println("\t" + getTodayTimestamp(10));
		System.out.println("getTodayTimestamp(timestamp)");
		System.out.println("\t" + getTodayTimestamp(timestamp));
		System.out.println("getTodayTimestamp(10, timestamp)");
		System.out.println("\t" + getTodayTimestamp(10, timestamp));
		Thread test1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName() + " : " + getSyncTimestamp());
				}
			}
		}, "getSyncTimestamp()线程1");
		Thread test2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName() + " : " + getSyncTimestamp());
				}
			}
		}, "getSyncTimestamp()线程2");
		test1.start();
		test2.start();
	}

	/**
	 * 获取时间戳
	 * 
	 * @param isSync 同步/异步
	 */
	public static long getTimestamp(boolean isSync) {
		if (isSync) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}
		return System.currentTimeMillis();
	}

	/**
	 * 获取时间戳
	 */
	public static long getTimestamp() {
		return getTimestamp(false);
	}

	/**
	 * 获取同步时间戳（1ms生成1个）
	 */
	public synchronized static long getSyncTimestamp() {
		return getTimestamp(true);
	}

	/**
	 * 日历实例（需要初始化）
	 */
	private static Calendar calendar = Calendar.getInstance();

	/**
	 * 获取今天的时间戳
	 * 
	 * @param isStart   开始/结束
	 * @param dayOffset 相对于今天的偏移量（天）
	 * @param timestamp 指定日期时间戳：<br>
	 *                  -1为当前时间
	 */
	public static long getTodayTimestamp(boolean isStart, int dayOffset, long timestamp) {
		if (timestamp == -1) {
			calendar = Calendar.getInstance();
		} else {
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
		if (dayOffset != 0) {
			calendar.add(Calendar.DAY_OF_YEAR, dayOffset);
		}
		return calendar.getTime().getTime();
	}

	/**
	 * 获取今天0时0分0秒0毫秒的时间戳
	 */
	public static long getTodayTimestamp() {
		return getTodayTimestamp(true, 0, -1);
	}

	/**
	 * 获取今天0时0分0秒0毫秒的时间戳
	 * 
	 * @param timestamp 指定日期时间戳
	 */
	public static long getTodayTimestamp(long timestamp) {
		return getTodayTimestamp(true, 0, timestamp);
	}

	/**
	 * 获取今天0时0分0秒0毫秒的时间戳
	 * 
	 * @param dayOffset 相对于今天的偏移量（天）
	 */

	public static long getTodayTimestamp(int dayOffset) {
		return getTodayTimestamp(true, dayOffset, -1);
	}

	/**
	 * 获取今天0时0分0秒0毫秒的时间戳
	 * 
	 * @param dayOffset 相对于今天的偏移量（天）
	 * @param timestamp 指定日期时间戳
	 */

	public static long getTodayTimestamp(int dayOffset, long timestamp) {
		return getTodayTimestamp(true, dayOffset, timestamp);
	}

	/**
	 * 获取今天23时59分59秒999毫秒的时间戳
	 */
	public static long getTodayEndTimestamp() {
		return getTodayTimestamp(false, 0, -1);
	}

	/**
	 * 获取今天23时59分59秒999毫秒的时间戳
	 * 
	 * @param timestamp 指定日期时间戳
	 */
	public static long getTodayEndTimestamp(long timestamp) {
		return getTodayTimestamp(false, 0, timestamp);
	}

	/**
	 * 获取今天23时59分59秒999毫秒的时间戳
	 * 
	 * @param dayOffset 相对于今天的偏移量（天）
	 */
	public static long getTodayEndTimestamp(int dayOffset) {
		return getTodayTimestamp(false, dayOffset, -1);
	}

	/**
	 * 获取今天23时59分59秒999毫秒的时间戳
	 * 
	 * @param dayOffset 相对于今天的偏移量（天）
	 * @param timestamp 指定日期时间戳
	 */
	public static long getTodayEndTimestamp(int dayOffset, long timestamp) {
		return getTodayTimestamp(false, dayOffset, timestamp);
	}

	/**
	 * 日期实例（需要初始化）
	 */
	private static Date date = new Date();

	/**
	 * 获取日期（自定义格式）
	 * 
	 * @param timestamp 时间戳：<br>
	 *                  -1为当前时间
	 * @param format    自定义格式: <br>
	 *                  G:纪元标记 yyyy:年 MM:月 dd:日 <br>
	 *                  hh:12小时制 HH:24小时制 mm:分 ss:秒 SS:毫秒 E:星期 z:时区<br>
	 *                  D:一年中第几天 F:一个月中第几天的周几 w:一年中的第几周 W:一个月中的第几周 <br>
	 *                  a:AM/PM标记 k:一天中的第几小时(1-24) K:AM/PM格式一天中的第几小时(0-11)<br>
	 *                  ':文本定界符 ":单引号
	 */
	public static String getDatetime(long timestamp, String format) {
		if (timestamp == -1) {
			date = new Date();
		} else {
			date = new Date(timestamp);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 获取日期（自定义格式）
	 * 
	 * @param format 自定义格式: <br>
	 *               G:纪元标记 yyyy:年 MM:月 dd:日 <br>
	 *               hh:12小时制 HH:24小时制 mm:分 ss:秒 SS:毫秒 E:星期 z:时区<br>
	 *               D:一年中第几天 F:一个月中第几天的周几 w:一年中的第几周 W:一个月中的第几周 <br>
	 *               a:AM/PM标记 k:一天中的第几小时(1-24) K:AM/PM格式一天中的第几小时(0-11)<br>
	 *               ':文本定界符 ":单引号
	 */
	public static String getDatetime(String format) {
		return getDatetime(-1, format);
	}

	/**
	 * 获取日期 yyyy-MM-dd HH:mm:ss格式
	 */
	public static String getDatetime() {
		return getDatetime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取日期 yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param timestamp 时间戳
	 */
	public static String getDatetime(long timestamp) {
		return getDatetime(timestamp, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取日期 yyyy-MM-dd格式
	 */
	public static String getDate() {
		return getDatetime("yyyy-MM-dd");
	}

	/**
	 * 获取日期 yyyy-MM-dd格式
	 * 
	 * @param timestamp 时间戳
	 */
	public static String getDate(long timestamp) {
		return getDatetime(timestamp, "yyyy-MM-dd");
	}

	/**
	 * 获取时间HH:mm:ss格式
	 */
	public static String getTime() {
		return getDatetime("HH:mm:ss");
	}

	/**
	 * 获取时间HH:mm:ss格式
	 * 
	 * @param timestamp 时间戳
	 */
	public static String getTime(long timestamp) {
		return getDatetime(timestamp, "HH:mm:ss");
	}

}
