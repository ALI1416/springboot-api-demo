package com.demo.config;

import com.demo.annotation.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <h1>Web配置</h1>
 *
 * <p>
 * createDate 2020/12/06 10:00:57
 * </p>
 *
 * @author ALI[1416978277@qq.com]
 * @since 1.0.0
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }
}