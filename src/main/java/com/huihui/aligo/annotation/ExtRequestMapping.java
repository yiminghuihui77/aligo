package com.huihui.aligo.annotation;

import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * @author minghui.y BG358486
 * @create 2020-10-09 14:34
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ExtRequestMapping {

    String value() default "";

    RequestMethod method() default RequestMethod.POST;
}
