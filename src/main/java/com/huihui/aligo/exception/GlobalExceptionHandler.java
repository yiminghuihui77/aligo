package com.huihui.aligo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器
 * @author minghui.y BG358486
 * @create 2020-10-17 13:58
 **/
@ControllerAdvice(basePackages = "com.huihui.aligo.controller")
public class GlobalExceptionHandler {


    @ExceptionHandler({ArithmeticException.class})
    public String handRuntimeException() {
        System.out.println("全局异常处理");
        return "home";
    }


}
