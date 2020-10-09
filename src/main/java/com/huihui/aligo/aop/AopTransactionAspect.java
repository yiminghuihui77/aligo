package com.huihui.aligo.aop;

import com.huihui.aligo.annotation.MyTransactional;
import com.huihui.aligo.transaction.TransactionHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义注解事务的切面
 *
 * @author minghui.y BG358486
 * @create 2020-10-07 12:14
 **/
@Aspect
@Component
public class AopTransactionAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopTransactionAspect.class);

    @Autowired
    private TransactionHelper transactionHelper;

    /**
     * 抛出异常后增强
     */
    @AfterThrowing("execution(* com.huihui.aligo.service.*.*.*(..))")
    public void afterThrowingMethod() {
        transactionHelper.rollback();
        LOGGER.error("代理方法抛出异常，事务回滚...");
    }

    /**
     * 环绕增强
     * @param proceedingJoinPoint
     */
    @Around("execution(* com.huihui.aligo.service.*.*.*(..))")
    public void aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //1、获取目标代理对象及其方法
        Class<?> clazz = proceedingJoinPoint.getTarget().getClass();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Class<?>[] params = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes();
        Method method = clazz.getMethod(methodName, params);

        //2、获取方法上的注解
        MyTransactional myTransactional = method.getDeclaredAnnotation(MyTransactional.class);

        //3、若存在自定义的事务注解，则方法执行前开启事务
        if (myTransactional != null) {
            transactionHelper.begin();
        }

        //4、执行目标方法
        proceedingJoinPoint.proceed();

        //5、提交事务
        if (myTransactional != null) {
            transactionHelper.commit();
        }
    }
}
