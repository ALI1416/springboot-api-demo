package com.demo.tool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1>线程池</h1>
 *
 * <p>
 * 线程池不允许关闭！
 * </p>
 *
 * <p>
 * createDate 2020/11/12 20:23:18
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 **/
public class ThreadPool {

    /**
     * 自定义线程池
     */
    private static final ExecutorService POOL = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), new NameTreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    /**
     * 执行线程
     *
     * @param command 线程
     */
    public static void execute(Runnable command) {
        POOL.execute(command);
    }

    /**
     * 自定义线程名
     */
    private static class NameTreadFactory implements ThreadFactory {
        private final AtomicInteger mThreadNumber = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ThreadPool-" + mThreadNumber.getAndIncrement());
        }
    }
}