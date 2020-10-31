package com.huihui.aligo.aop;

import com.google.common.util.concurrent.RateLimiter;
import com.huihui.aligo.annotation.ExtRateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 限流切面
 *
 * @author minghui.y BG358486
 * @create 2020-10-30 21:09
 **/
@Component
@Aspect
public class RateLimitAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitAspect.class);

    private Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();


    /**
     * 拦截所有接口层
     */
    @Pointcut("execution(public * com.huihui.aligo.controller.*.*(..))")
    public void webLogRecord() {
    }


    @Around("webLogRecord()")
    public void rateLimitBeforeExecute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //1、获取目标代理对象及其方法
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method  = methodSignature.getMethod();

        //判断method是否注解@ExtRateLimit
        ExtRateLimit extRateLimit = method.getAnnotation(ExtRateLimit.class);

        if (extRateLimit != null) {
            double permitsPerSecond = extRateLimit.permitsPerSecond();
            long timeOut = extRateLimit.timeOut();
            TimeUnit timeUnit = extRateLimit.timeUnit();

            //获取请求信息
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            //同一个请求URL公用一个令牌桶
            RateLimiter rateLimiter = rateLimiterMap.get(request.getRequestURI());
            if (rateLimiter == null) {
                rateLimiter = RateLimiter.create(permitsPerSecond);
                rateLimiterMap.put(request.getRequestURI(), rateLimiter);
            }


            boolean flag = rateLimiter.tryAcquire(timeOut, timeUnit);
            if (!flag) {
                LOGGER.error("time out for a token, execute fallback");

                HttpServletResponse response = attributes.getResponse();
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().println("当前接口并发量大，执行服务降级操作...");
                return;
            }
        }

        //执行目标方法
        proceedingJoinPoint.proceed();

    }
}
