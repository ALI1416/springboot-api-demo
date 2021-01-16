package com.demo.constant;

import lombok.Getter;

/**
 * <h1>返回结果状态</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
public enum ResultCodeEnum {

    /* ==================== 成功0 ==================== */
    /**
     * 成功0
     */
    OK(0, "成功"),

    /* ==================== 通用100-999 ==================== */
    /**
     * 系统繁忙100
     */
    SYSTEM_INNER_ERROR(100, "系统繁忙"),
    /**
     * 参数错误101
     */
    PARAM_IS_ERROR(101, "参数错误"),
    /**
     * 非法操作102
     */
    INVALID_OPERATION(102, "非法操作"),
    /**
     * 批量操作异常103
     */
    BATCH_OPERATION_ERROR(103, "批量操作异常"),
    /**
     * API找不到104
     */
    NOT_FOUND(104, "接口找不到"),
    /**
     * 方法不支持105
     */
    NOT_SUPPORTED(105, "方法不支持"),

    /* ==================== 用户相关1000-1999 ==================== */
    /**
     * token过期1000
     */
    TOKEN_IS_EXPIRED(1000, "token过期"),
    /**
     * 用户离线1000
     */
    USER_OFFLINE(1000, "用户离线"),
    /**
     * 用户未登录或登录已过期1001
     */
    USER_NOT_LOGGED_IN(1001, "账号未登录或登录已过期"),
    /**
     * 账号不存在或密码错误1002
     */
    USER_LOGIN_ERROR(1002, "账号不存在或密码错误"),
    /**
     * 账号已禁用或已注销1003
     */
    USER_ACCOUNT_FORBIDDEN(1003, "账号已禁用或已注销"),
    /**
     * 用户不存在1004
     */
    USER_NOT_EXIST(1004, "账号不存在"),
    /**
     * 用户已存在1005
     */
    USER_HAS_EXISTED(1005, "账号已存在"),
    /**
     * 第三方登录失败1006
     */
    THIRD_PARTY_LOGIN_FAILED(1006, "第三方登录失败"),

    /* ==================== 未知错误-1 ==================== */
    /**
     * 未知错误-1
     */
    ERROR(-1, "未知错误");

    /* ==================== 枚举字段和构造器 ==================== */
    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态信息
     */
    private final String msg;

    /**
     * 构造函数
     *
     * @param code 状态码
     * @param msg  信息
     */
    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}