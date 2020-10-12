package com.huihui.aligo.annotation;

import java.lang.annotation.*;

/**
 * 自定义插入注解
 *
 * @author minghui.y
 * @create 2020-10-12 11:19
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtInsert {
    String value();
}
