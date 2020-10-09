package com.huihui.aligo.aop;

import com.huihui.aligo.so.StoreSo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 切面：定义切点和通知
 * @author minghui.y BG358486
 * @create 2020-10-06 19:02
 **/
@Component
@EnableAspectJAutoProxy
@Aspect
public class AspectLogger {

    @Before("execution(* com.huihui.aligo.service.StoreService.getStoreBySo(..))")
    public void beforeMethod() {
        System.out.println("我是前置通知...");
    }

    @After("execution(* com.huihui.aligo.service.StoreService.getStoreBySo(..))")
    public void afterMethod() {
        System.out.println("我是后置通知...");
    }

    @AfterReturning("execution(* com.huihui.aligo.service.StoreService.getStoreBySo(..))")
    public void afterReturnMethod() {
        System.out.println("我是返回后通知...");
    }

    @AfterThrowing("execution(* com.huihui.aligo.service.StoreService.getStoreBySo(..))")
    public void afterThrowingMethod() {
        System.out.println("我是抛出后异常通知...");
    }

    @Around("execution(* com.huihui.aligo.service.StoreService.searchStores(..))")
    public void aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("我是环绕通知的前置...");
        //如果被调用的方法抛出异常，环绕通知的后置是不会被执行的（事务失效的原因）；若try-catch掉异常，会执行
        proceedingJoinPoint.proceed();
        System.out.println("我是环绕通知的后置...");
    }
}
