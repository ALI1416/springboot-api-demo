package com.demo.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <h1>线程池</h1>
 *
 * <p>createDate 2020/11/12 20:23:18</p>
 *
 * @author ALI[1416978277@qq.com]
 **/
public class ThreadPool {
    /**
     * 无限量线程池
     */
    private static ExecutorService pool = Executors.newCachedThreadPool();

    /**
     * 执行线程
     *
     * @param command 线程
     */
    public static void execute(Runnable command) {
        // 如果线程池已关闭，则重新创建一个新的
        if (pool.isShutdown()) {
            pool = Executors.newCachedThreadPool();
        }
        pool.execute(command);
    }

    /**
     * 关闭线程池
     */
    public static void shutdown() {
        pool.shutdown();
    }

    /**
     * 强制关闭线程池
     */
    public static void shutdownNow() {
        pool.shutdownNow();
    }
}
