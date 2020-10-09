package com.huihui.aligo.annotation;

import java.lang.annotation.*;

/**
 * 自定义bean注解
 *
 * @author minghui.y BG358486
 * @create 2020-10-08 10:52
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExtComponent {

    String name() default "";
}
