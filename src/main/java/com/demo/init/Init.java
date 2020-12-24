package com.demo.init;

import com.demo.tool.Id;
import com.demo.util.PhoneUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final static Logger log = LoggerFactory.getLogger(Task.class);

    public static void main(String[] args) {
        initial();
    }

    public static void initial() {
        log.info("初始化任务开始...");
        AnsjTask.initial();
        Ip2RegionTask.initial();
        phoneNumberLookupTaskInitial();
        idTaskInitial();
        log.info("初始化任务结束。");
    }

    /**
     * 手机号码信息任务
     */
    public static void phoneNumberLookupTaskInitial() {
        log.info("PhoneNumberLookup初始化任务开始...");
        log.info("PhoneNumberLookup开始加载...");
        log.info("PhoneNumberLookup测试：" + PhoneUtils.getPhoneInfo("18754710000"));
        log.info("PhoneNumberLookup加载结束。");
        log.info("PhoneNumberLookup初始化任务结束。");
    }

    /**
     * 高性能ID生成器任务
     */
    public static void idTaskInitial() {
        log.info("高性能Id生成器初始化任务开始...");
        log.info("高性能Id生成器开始加载...");
        log.info("高性能Id生成器测试：" + Id.next());
        log.info("高性能Id生成器加载结束。");
        log.info("高性能Id生成器初始化任务结束。");
    }


}
