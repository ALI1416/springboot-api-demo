package com.demo.result;

/**
 * <h1>返回结果状态类</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public enum ResultCode {

    /* 成功0 */
    /**
     * 成功0
     */
    OK(0, "成功"),

    /* 通用100-999 */
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

    /* 用户相关1000-1999 */
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
     * 批量注册异常1006
     */
    USER_BATCH_REGISTER_ERROR(1006, "批量注册异常"),

    /* 未知错误-1 */
    /**
     * 未知错误-1
     */
    ERROR(-1, "未知错误");

    /**
     * 状态码
     */
    private final int CODE;
    /**
     * 状态信息
     */
    private final String MSG;

    /**
     * 构造函数
     *
     * @param code 状态码
     * @param msg  信息
     */
    ResultCode(int code, String msg) {
        CODE = code;
        MSG = msg;
    }

    /**
     * 获取状态码
     */
    public int code() {
        return CODE;
    }

    /**
     * 获取状态信息
     */
    public String msg() {
        return MSG;
    }

}