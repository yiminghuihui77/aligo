package com.huihui.aligo.annotation;

import java.lang.annotation.*;

/**
 * 自定义Controller注解
 *
 * @author minghui.y BG358486
 * @create 2020-10-09 14:32
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExtController {
    String value() default "";
}
