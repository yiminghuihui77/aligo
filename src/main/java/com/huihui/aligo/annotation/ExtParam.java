package com.huihui.aligo.annotation;

import java.lang.annotation.*;

/**
 * 参数注解
 *
 * @author minghui.y BG358486
 * @create 2020-10-11 12:16
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ExtParam {
    String value();
}
