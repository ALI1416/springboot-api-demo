package com.demo.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

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
     * 线程池命名
     */
    private static final ThreadFactory NAMED_THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d")
            .build();

    /**
     * 自定义线程池
     */
    private static final ExecutorService POOL = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), NAMED_THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 执行线程
     *
     * @param command 线程
     */
    public static void execute(Runnable command) {
        POOL.execute(command);
    }

}
