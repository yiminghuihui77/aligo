package com.huihui.aligo.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 接口限流注解
 *
 * @author minghui.y BG358486
 * @create 2020-10-30 21:02
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ExtRateLimit {

    /**
     * 每秒产生令牌数(速率)
     * @return
     */
    double permitsPerSecond();

    /**
     * 允许等待的时间
     * @return
     */
    long timeOut();

    /**
     * 时间单位
     * @return
     */
    TimeUnit timeUnit();


}
