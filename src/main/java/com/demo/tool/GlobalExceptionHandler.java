package com.demo.tool;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.demo.constant.ResultCodeEnum;
import com.demo.entity.pojo.Result;

/**
 * <h1>全局异常捕获</h1>
 *
 * <p>
 * 捕获所有未被捕获的异常
 * </p>
 *
 * <p>
 * createDate 2020/11/28 17:06:12
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * <h3>参数错误</h3><br>
     * Params<br>
     * --{@linkplain java.lang.IllegalStateException
     * IllegalStateException}：非法的状态异常：缺少参数<br>
     * --{@linkplain org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
     * MethodArgumentTypeMismatchException}：方法参数类型不匹配异常：参数类型错误<br>
     * Body<br>
     * --form-data<br>
     * --x-www-form-urlencoded<br>
     * ----{@linkplain org.springframework.validation.BindException
     * BindException}：绑定异常：参数类型错误<br>
     * --raw<br>
     * ----JSON<br>
     * ------{@linkplain org.springframework.http.converter.HttpMessageNotReadableException
     * HttpMessageNotReadableException}：Http消息不可读异常：JSON对象不存在或JSON对象格式错误或参数类型错误<br>
     * ----XML<br>
     * ----HTML<br>
     * ----Text<br>
     * --binary<br>
     */
    @Order(1)
    @ExceptionHandler({ IllegalStateException.class, MethodArgumentTypeMismatchException.class, BindException.class,
            HttpMessageNotReadableException.class })
    public Result paramErrorHandler(Exception e) {
        System.out.println("paramErrorHandler");
        e.printStackTrace();
        return Result.e(ResultCodeEnum.PARAM_IS_ERROR);
    }

    /**
     * 运行异常
     */
    @Order(2)
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(Exception e) {
        System.out.println("runtimeExceptionHandler");
        e.printStackTrace();
        return Result.e(ResultCodeEnum.ERROR, "运行异常");
    }

    /**
     * IO异常
     */
    @Order(2)
    @ExceptionHandler(IOException.class)
    public Result iOExceptionHandler(Exception e) {
        System.out.println("iOExceptionHandler");
        e.printStackTrace();
        return Result.e(ResultCodeEnum.ERROR, "IO异常");
    }

    /**
     * 异常
     */
    @Order(3)
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        System.out.println("exceptionHandler");
        e.printStackTrace();
        return Result.e(ResultCodeEnum.ERROR, "异常");
    }
}
