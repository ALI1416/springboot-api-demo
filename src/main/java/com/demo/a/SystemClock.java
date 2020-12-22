package com.demo.a;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 高并发场景下System.currentTimeMillis()的性能问题的优化
 * </p>
 * <p>
 * System.currentTimeMillis()的调用比new一个普通对象要耗时的多（具体耗时高出多少我还没测试过，有人说是100倍左右）<br>
 * System.currentTimeMillis()之所以慢是因为去跟系统打了一次交道<br>
 * 后台定时更新时钟，JVM退出时，线程自动回收<br>
 * 10亿：43410,206,210.72815533980582%<br>
 * 1亿：4699,29,162.0344827586207%<br>
 * 1000万：480,12,40.0%<br>
 * 100万：50,10,5.0%<br>
 * </p>
 *
 * @author hubin
 * @Date 2016-08-01
 */
public class SystemClock {

    public static void main(String[] args) {
        System.out.println(now());
    }

    /**
     * 获取时间戳
     */
    public static long now() {
        return INSTANCE.now.get();
    }

    /**
     * 现在时间戳
     */
    private AtomicLong now = new AtomicLong(System.currentTimeMillis());
    /**
     * 系统时钟实例
     */
    private static final SystemClock INSTANCE = new SystemClock();

    /**
     * 每1毫秒更新一次时间戳
     */
    private SystemClock() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "SystemClock");
                thread.setDaemon(true);
                return thread;
            }
        });
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                now.set(System.currentTimeMillis());
            }
        }, 1, 1, TimeUnit.MILLISECONDS);
    }
}
