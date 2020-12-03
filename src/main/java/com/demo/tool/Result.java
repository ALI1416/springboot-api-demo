package com.demo.tool;

import lombok.Getter;
import lombok.ToString;

/**
 * <h1>返回结果</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@ToString
public class Result {

    /**
     * 成功(状态码为0)
     */
    private final boolean ok;
    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态信息
     */
    private final String msg;
    /**
     * 数据
     */
    private final Object data;

    /**
     * 构造函数
     *
     * @param resultCode 状态
     * @param obj        数据
     */
    private Result(ResultCode resultCode, Object obj) {
        code = resultCode.code;
        msg = resultCode.msg;
        data = obj;
        ok = (code == ResultCode.OK.code);
    }

    /**
     * 成功
     */
    public static Result o() {
        return new Result(ResultCode.OK, null);
    }

    /**
     * 成功
     *
     * @param obj 数据
     */
    public static Result o(Object obj) {
        return new Result(ResultCode.OK, obj);
    }

    /**
     * 错误
     *
     * @param resultCode 状态
     */
    public static Result e(ResultCode resultCode) {
        return new Result(resultCode, null);
    }

    /**
     * 错误
     *
     * @param resultCode 状态
     * @param obj        数据
     */
    public static Result e(ResultCode resultCode, Object obj) {
        return new Result(resultCode, obj);
    }

    /**
     * 错误100系统繁忙
     */
    public static Result e() {
        return new Result(ResultCode.SYSTEM_INNER_ERROR, null);
    }

    /**
     * 错误101参数错误
     */
    public static Result e1() {
        return new Result(ResultCode.PARAM_IS_ERROR, null);
    }

    /**
     * 错误102非法操作
     */
    public static Result e2() {
        return new Result(ResultCode.INVALID_OPERATION, null);
    }

}
