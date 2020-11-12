package com.demo.tool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import javax.annotation.Nonnull;
import java.util.concurrent.*;

/**
 * <h1>线程池</h1>
 *
 * <p>createDate 2020/11/12 20:23:18</p>
 *
 * @author ALI[1416978277@qq.com]
 **/
public class ThreadPool {
    /**
     * 线程池命名
     */
    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    /**
     * 自定义线程池
     */
    private static ExecutorService pool = null;

    /**
     * 创建线程池
     */
    private static void create() {
        pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 执行线程
     *
     * @param command 线程
     */
    public static void execute(Runnable command) {
        // 如果线程池已关闭，则重新创建一个新的
        if (pool == null || pool.isShutdown()) {
            create();
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
