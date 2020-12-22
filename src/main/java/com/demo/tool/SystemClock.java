package com.demo.tool;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h1>高性能系统时钟</h1>
 *
 * <p>
 * createDate 2020/12/22 19:53:00
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class SystemClock {

    public static void main(String[] args) {
        System.out.println(now());
    }

    /**
     * 现在时间戳(原子长整形，防止多线程异常)
     */
    private final AtomicLong now = new AtomicLong(System.currentTimeMillis());
    /**
     * 系统时钟实例
     */
    private static final SystemClock instance = new SystemClock();

    /**
     * 获取当前时间戳
     */
    public static long now() {
        return instance.now.get();
    }

    /**
     * 每1毫秒更新一次时间戳
     */
    private SystemClock() {
        Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "SystemClock");
            thread.setDaemon(true);
            return thread;
        }).scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), 1, 1, TimeUnit.MILLISECONDS);
    }
}
