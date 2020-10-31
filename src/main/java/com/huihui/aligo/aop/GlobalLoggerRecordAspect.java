package com.huihui.aligo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * 全局日志记录切面
 *
 * @author minghui.y BG358486
 * @create 2020-10-17 16:50
 **/
//@Component
//@Aspect
@Slf4j
public class GlobalLoggerRecordAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalLoggerRecordAspect.class);

    @Pointcut("execution(public * com.huihui.aligo.controller.*.*(..))")
    public void webLogRecord() {
    }


    @Before("webLogRecord()")
    public void recordBefore() {
        //获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //日志记录请求信息
        LOGGER.info("请求URL:{}", request.getRequestURL().toString());
        LOGGER.info("请求METHOD:{}", request.getMethod());
        LOGGER.info("请求IP:{}", request.getRemoteAddr());

        //请求参数记录
        StringBuffer buffer = new StringBuffer();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getParameter(key);
            buffer.append("[" + key + ": " + value + "] ");
        }
        LOGGER.info("请求参数: {}", buffer.toString());


    }


    @AfterReturning(returning = "result", pointcut = "webLogRecord()")
    public void recordAfterReturn(Object result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //日志记录请求信息
        LOGGER.info("请求：{} 的响应结果：{}", request.getRequestURL().toString(), result);
    }

}
