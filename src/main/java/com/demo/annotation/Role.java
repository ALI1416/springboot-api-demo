package com.demo.annotation;

import java.lang.annotation.*;

/**
 * <h1>api角色验证注解</h1>
 *
 * <p>
 * createDate 2021/01/24 15:04:18
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Role {

    /**
     * 不进行角色验证
     */
    boolean skip() default false;

}
