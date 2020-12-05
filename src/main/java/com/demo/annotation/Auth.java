package com.demo.annotation;

import java.lang.annotation.*;

/**
 * <h1>权限认证注解</h1>
 *
 * <h2>@Target      表明该注解可以应用的Java元素类型</h2>
 * <p>
 * ElementType.TYPE             应用于类、接口（包括注解类型）、枚举<br>
 * ElementType.FIELD            应用于属性（包括枚举中的常量）<br>
 * ElementType.METHOD           应用于方法<br>
 * ElementType.PARAMETER        应用于方法的形参<br>
 * ElementType.CONSTRUCTOR      应用于构造函数<br>
 * ElementType.LOCAL_VARIABLE   应用于局部变量<br>
 * ElementType.ANNOTATION_TYPE  应用于注解类型<br>
 * ElementType.PACKAGE          应用于包<br>
 * ElementType.TYPE_PARAMETER   应用于类型变量<br>
 * ElementType.TYPE_USE         应用于任何使用类型的语句中（例如声明语句、泛型和强制转换语句中的类型）
 * </p>
 * <h2>@Retention   表明该注解的生命周期<h2>
 * <p>
 * RetentionPolicy.SOURCE   编译时被丢弃，不包含在类文件中<br>
 * RetentionPolicy.CLASS    JVM加载时被丢弃，包含在类文件中，默认值<br>
 * RetentionPolicy.RUNTIME  由JVM加载，包含在类文件中，在运行时可以被获取到
 * </p>
 * <h2>@Document    表明该注解标记的元素可以被Javadoc或类似的工具文档化<h2>
 * <h2>@Inherited   表明使用了@Inherited注解的注解，所标记的类的子类也会拥有这个注解<h2>
 *
 * <p>
 * createDate 2020/12/05 19:20:09
 * </p>
 *
 * @author ALI[ali-k@foxmail.com]
 * @since 1.0.0
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
    /**
     * 是否跳过类中的某个方法
     */
    boolean skip() default false;
}
