package com.demo.util.pojo;

import lombok.Getter;

/**
 * <h1>Mime类别</h1>
 *
 * <p>
 * createDate 2021/02/17 11:23:57
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
public enum MimeCategory {

    /**
     * 应用
     */
    APPLICATION("application", "应用"),
    /**
     * 音频
     */
    AUDIO("audio", "音频"),
    /**
     * 字体
     */
    FONT("font", "字体"),
    /**
     * 图片
     */
    IMAGE("image", "图片"),
    /**
     * 消息
     */
    MESSAGE("message", "消息"),
    /**
     * 模型
     */
    MODEL("model", "模型"),
    /**
     * 多文件
     */
    MULTIPART("multipart", "多文件"),
    /**
     * 文本
     */
    TEXT("text", "文本"),
    /**
     * 视频
     */
    VIDEO("video", "视频"),
    ;

    /**
     * 类别
     */
    private final String category;
    /**
     * 简介
     */
    private final String profile;

    /**
     * 构造函数
     *
     * @param category 类别
     * @param profile  简介
     */
    MimeCategory(String category, String profile) {
        this.category = category;
        this.profile = profile;
    }

}
