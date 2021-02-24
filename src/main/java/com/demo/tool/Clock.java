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
public class Clock {

    /**
     * 系统时钟实例
     */
    private final static Clock INSTANCE = new Clock();
    /**
     * 现在时间戳(原子长整形，防止多线程异常)
     */
    private final AtomicLong now = new AtomicLong(System.currentTimeMillis());
    /**
     * 每1毫秒更新一次时间戳
     */
    private Clock() {
        Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "SystemClock");
            thread.setDaemon(true);
            return thread;
        }).scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), 1, 1, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        int count = 100000000;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 高性能时钟
        long a = Clock.now();
        for (int i = 0; i < count; i++) {
            now();
        }
        long b = Clock.now();
        long ba = b - a;
        System.out.println(ba);

        // 系统时钟
        long c = Clock.now();
        for (int i = 0; i < count; i++) {
            System.currentTimeMillis();
        }
        long d = Clock.now();
        long dc = d - c;
        System.out.println(dc);

        System.out.println(dc / (double) ba);
    }

    /**
     * 获取当前时间戳
     */
    public static long now() {
        return INSTANCE.now.get();
    }
}
