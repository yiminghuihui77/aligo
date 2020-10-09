package com.huihui.aligo.annotation;

import java.lang.annotation.*;

/**
 * 事务注解
 *
 * @author minghui.y BG358486
 * @create 2020-10-07 9:47
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface MyTransactional {
}



