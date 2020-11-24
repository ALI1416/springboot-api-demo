package com.demo.result;

import lombok.Getter;
import lombok.Setter;
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
@Setter
@ToString
public class Result {

    /**
     * 状态码
     */
    private int code;
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    /**
     * 构造函数
     * 
     * @param resultCode 状态
     * @param data       数据
     */
    private Result(ResultCode resultCode, Object data) {
        this.code = resultCode.code();
        this.msg = resultCode.msg();
        this.data = data;
    }

    /**
     * 成功
     */
    public static Result ok() {
        return new Result(ResultCode.OK, null);
    }

    /**
     * 成功
     * 
     * @param data 数据
     */
    public static Result ok(Object data) {
        return new Result(ResultCode.OK, data);
    }

    /**
     * 未知错误
     */
    public static Result e() {
        return new Result(ResultCode.ERROR, null);
    }

    /**
     * 未知错误
     * 
     * @param data 数据
     */
    public static Result e(Object data) {
        return new Result(ResultCode.ERROR, data);
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
     * @param data       数据
     */
    public static Result e(ResultCode resultCode, Object data) {
        return new Result(resultCode, data);
    }
}
