package com.huihui.aligo.annotation;

import java.lang.annotation.*;

/**
 * 依赖注入注解
 *
 * @author minghui.y BG358486
 * @create 2020-10-08 14:33
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExtAutowire {
}
