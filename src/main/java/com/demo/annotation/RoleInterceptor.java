package com.demo.annotation;

import com.demo.entity.pojo.Result;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * <h1>api角色验证注解拦截器</h1>
 *
 * <p>
 * createDate 2021/01/24 15:06:56
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler) throws IOException {
        if (handler instanceof HandlerMethod) {
            // 获取方法
            Method method = ((HandlerMethod) handler).getMethod();
            // 获取类
            Class<?> clazz = method.getDeclaringClass();
            // 方法的注解
            Auth authMethod = method.getAnnotation(Auth.class);
            // 类的注解
            Auth authClazz = clazz.getAnnotation(Auth.class);
            /* 没有注解，放过 */
            if (authClazz == null && authMethod == null) {
                return true;
            }
            // 查找所需权限
            boolean skip = false;
            boolean skipLogin = false;
            if (authClazz != null) {
                skip = authClazz.skip();
                skipLogin = authClazz.skipLogin();
            }
            if (authMethod != null) {
                skip = authMethod.skip();
                skipLogin = authMethod.skipLogin();
            }
            /* 不需权限，跳过 */
            if (skip) {
                return true;
            }
            // 所需权限，由多到少依次判断
            /* 需要登录权限 */
            if (!skipLogin) {
                Result result = AuthImpl.login(request);
                if (result.isOk()) {
                    return true;
                } else {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print(result);
                    out.flush();
                    out.close();
                    return false;
                }
            }
            /* 需要普通权限 */
            Result result = AuthImpl.token(request);
            if (result.isOk()) {
                return true;
            } else {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.print(result);
                out.flush();
                out.close();
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest httpServletRequest,
            @NonNull HttpServletResponse httpServletResponse, @NonNull Object object, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest httpServletRequest,
            @NonNull HttpServletResponse httpServletResponse, @NonNull Object object, Exception e) {

    }
}
