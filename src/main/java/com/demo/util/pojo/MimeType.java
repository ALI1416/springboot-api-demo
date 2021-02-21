package com.demo.util.pojo;

import lombok.Getter;

/**
 * <h1>Mime类型</h1>
 *
 * <p>
 * createDate 2021/02/17 12:00:29
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
public enum MimeType {

    /**
     * aac
     */
    AAC("aac", "audio/aac", "", MimeCategory.AUDIO),
    ;

    /**
     * 后缀
     */
    private final String suffix;
    /**
     * MIME
     */
    private final String mime;
    /**
     * 简介
     */
    private final String profile;
    /**
     * 类别
     */
    private final MimeCategory category;

    /**
     * 构造函数
     *
     * @param suffix   后缀
     * @param mime     MIME
     * @param profile  简介
     * @param category 类别
     */
    MimeType(String suffix, String mime, String profile, MimeCategory category) {
        this.suffix = suffix;
        this.mime = mime;
        this.profile = profile;
        this.category = category;
    }

}
