package com.demo.tool;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.e(ResultCode.ERROR);
    }
}
