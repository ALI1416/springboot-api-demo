package com.demo.entity;

import com.demo.constant.ResultCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

/**
 * <h1>返回结果实体</h1>
 *
 * <p>
 * createDate 2020/11/11 11:11:11
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result extends Base {

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
     */
    private Result(ResultCodeEnum resultCodeEnum, Object data) {
        this.code = resultCodeEnum.code;
        this.msg = resultCodeEnum.msg;
        this.data = data;
        ok = (code == ResultCodeEnum.OK.code);
    }

    /**
     * 构造函数
     */
    private Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        ok = (code == ResultCodeEnum.OK.code);
    }

    /**
     * 成功
     */
    public static Result o() {
        return new Result(ResultCodeEnum.OK, null);
    }

    /**
     * 成功
     */
    public static Result o(Object data) {
        return new Result(ResultCodeEnum.OK, data);
    }

    /**
     * 成功
     */
    public static Result o(String msg) {
        return new Result(ResultCodeEnum.OK.code, msg, null);
    }

    /**
     * 成功
     */
    public static Result o(String msg, Object data) {
        return new Result(ResultCodeEnum.OK.code, msg, data);
    }

    /**
     * 错误
     */
    public static Result e(ResultCodeEnum resultCodeEnum) {
        return new Result(resultCodeEnum, null);
    }

    /**
     * 错误
     */
    public static Result e(ResultCodeEnum resultCodeEnum, Object data) {
        return new Result(resultCodeEnum, data);
    }

    /**
     * 错误
     */
    public static Result e(ResultCodeEnum resultCodeEnum, String msg) {
        return new Result(resultCodeEnum.code, msg, null);
    }

    /**
     * 错误
     */
    public static Result e(ResultCodeEnum resultCodeEnum, String msg, Object data) {
        return new Result(resultCodeEnum.code, msg, data);
    }

    /**
     * 错误100系统繁忙
     */
    public static Result e() {
        return new Result(ResultCodeEnum.SYSTEM_INNER_ERROR, null);
    }

    /**
     * 错误101参数错误
     */
    public static Result e1() {
        return new Result(ResultCodeEnum.PARAM_IS_ERROR, null);
    }

    /**
     * 错误102非法操作
     */
    public static Result e2() {
        return new Result(ResultCodeEnum.INVALID_OPERATION, null);
    }

}
