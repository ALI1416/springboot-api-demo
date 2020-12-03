package com.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.util.PhoneUtils;

/**
 * <h1>总任务</h1>
 *
 * <p>
 * createDate 2020/11/21 09:28:54
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class Task {

    private final static Logger logger = LoggerFactory.getLogger(Task.class);

    public static void main(String[] args) {
        initial();
    }

    public static void initial() {
        logger.info("初始化任务开始...");
        AnsjTask.initial();
        Ip2RegionTask.initial();
        phoneNumberLookupTaskInitial();
        logger.info("初始化任务结束。");
    }

    public static void phoneNumberLookupTaskInitial() {
        logger.info("PhoneNumberLookup初始化任务开始...");
        logger.info("PhoneNumberLookup开始加载...");
        logger.info("PhoneNumberLookup测试：" + PhoneUtils.getPhoneInfo("18754710000"));
        logger.info("PhoneNumberLookup加载结束。");
        logger.info("PhoneNumberLookup初始化任务结束。");
    }

}
