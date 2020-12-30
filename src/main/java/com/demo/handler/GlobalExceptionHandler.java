package com.demo.handler;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.demo.constant.ResultCodeEnum;
import com.demo.entity.pojo.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * <h1>全局异常处理</h1>
 *
 * <p>
 * 捕获处理所有未被捕获的异常(404除外)
 * </p>
 *
 * <p>
 * createDate 2020/12/22 19:56:13
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * <h3>参数错误</h3><br>
     * Params<br>
     * --{@linkplain java.lang.IllegalStateException IllegalStateException},
     * {@linkplain java.lang.IllegalArgumentException
     * IllegalArgumentException}：非法状态异常、非法参数异常：缺少参数<br>
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
     *
     * @see java.lang.IllegalStateException
     * @see org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
     * @see org.springframework.http.converter.HttpMessageNotReadableException
     */
    @Order(1)
    @ExceptionHandler({ IllegalStateException.class, IllegalArgumentException.class,
            MethodArgumentTypeMismatchException.class, BindException.class, HttpMessageNotReadableException.class })
    public Result paramErrorHandler(Exception e) {
        System.out.println("paramErrorHandler");
        log.error("参数错误", e);
        return Result.e(ResultCodeEnum.PARAM_IS_ERROR);
    }

    /**
     * 方法Method不支持<br>
     * 例如某个接口要使用POST访问，对方使用GET去访问，会报错
     *
     * @see org.springframework.web.HttpRequestMethodNotSupportedException
     */
    @Order(1)
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public Result notSupportedHandler(Exception e) {
        System.out.println("notSupportedHandler");
        log.error("方法Method不支持", e);
        return Result.e(ResultCodeEnum.NOT_SUPPORTED);
    }

    /**
     * RuntimeException运行异常
     *
     * @see java.lang.RuntimeException
     */
    @Order(2)
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(Exception e) {
        System.out.println("runtimeExceptionHandler");
        log.error("RuntimeException运行异常", e);
        return Result.e(ResultCodeEnum.ERROR, "RuntimeException运行异常");
    }

    /**
     * IOException异常
     *
     * @see java.io.IOException
     */
    @Order(2)
    @ExceptionHandler(IOException.class)
    public Result ioExceptionHandler(Exception e) {
        System.out.println("ioExceptionHandler");
        log.error("IOException异常", e);
        return Result.e(ResultCodeEnum.ERROR, "IOException异常");
    }

    /**
     * Exception异常
     *
     * @see java.lang.Exception
     */
    @Order(3)
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        System.out.println("exceptionHandler");
        log.error("Exception异常", e);
        return Result.e(ResultCodeEnum.ERROR, "Exception异常");
    }
}
