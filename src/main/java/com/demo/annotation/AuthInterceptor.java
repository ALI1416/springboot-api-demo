package com.demo.annotation;

import com.demo.constant.ResultCodeEnum;
import com.demo.entity.Result;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * <h1>权限认证注解拦截器</h1>
 *
 * <p>
 * createDate 2020/12/05 20:05:23
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (handler instanceof HandlerMethod) {
            // 获取方法
            Method method = ((HandlerMethod) handler).getMethod();
            // 获取类
            Class<?> clazz = method.getDeclaringClass();
            // 方法的注解
            Auth authMethod = method.getAnnotation(Auth.class);
            // 类的注解
            Auth authClazz = clazz.getAnnotation(Auth.class);
            if ((authClazz == null && authMethod == null) || (authMethod != null && authMethod.skip())) {
                // 类和方法都没注解 或 方法注解跳过 不拦截
                return true;
            } else {
                // 进行拦截
                if (AuthImpl.authToken(request.getHeader("id"), request.getHeader("token"))) {
                    // token验证成功
                    return true;
                } else {
                    // token验证失败
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    // 返回错误信息
                    out.print(Result.e(ResultCodeEnum.USER_NOT_LOGGED_IN));
                    out.flush();
                    out.close();
                    return false;
                }
            }
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
