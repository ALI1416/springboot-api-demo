package com.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>总任务类</h1>
 *
 * <p>
 * createDate 2020/11/21 09:28:54
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 **/
public class Task {

    private final static Logger logger = LoggerFactory.getLogger(Task.class);

    public static void initial() {
        logger.info("初始化任务开始...");
        AnsjTask.initial();
        Ip2RegionTask.initial();
        logger.info("初始化任务结束。");
    }

}
