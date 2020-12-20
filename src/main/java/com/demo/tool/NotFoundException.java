package com.demo.tool;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.constant.ResultCodeEnum;
import com.demo.entity.pojo.Result;

@RestController
public class NotFoundException implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Result error(HttpServletRequest request) {
        return Result.e(ResultCodeEnum.NOT_FOUND);
    }
}
