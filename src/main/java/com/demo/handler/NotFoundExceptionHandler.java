package com.demo.handler;

import com.demo.constant.ResultCodeEnum;
import com.demo.entity.pojo.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>404异常捕获</h1>
 *
 * <p>
 * createDate 2020/11/28 17:06:12
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@RestController
public class NotFoundExceptionHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Result error() {
        return Result.e(ResultCodeEnum.NOT_FOUND);
    }
}
