package com.huihui.aligo.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.huihui.aligo.annotation.ExtRateLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 令牌桶限流Controller
 *
 * @author minghui.y BG358486
 * @create 2020-10-29 22:56
 **/
@RestController
@RequestMapping("/limit")
public class FlowLimitController {

    /**
     * 令牌生成器
     */
    private RateLimiter rateLimiter = RateLimiter.create(1);


    @RequestMapping(value = "/orderSku", method = RequestMethod.GET)
    public String orderSku() {

        //尝试获取令牌，没有可用令牌执行等待
//        double waitTime = rateLimiter.acquire();

        boolean flag = rateLimiter.tryAcquire(5000, TimeUnit.MICROSECONDS);
        if (!flag) {
            System.out.println("500毫秒内未抢到，执行服务降级...");
            return "error";
        }


        if (order()) {
            System.out.println("恭喜您成功抢到该商品，耗时：");
        } else {
            System.out.println("抱歉，您未抢到该商品...");
        }

        return "success";
    }


    @RequestMapping(value = "/orderSkuByAuto", method = RequestMethod.GET)
    @ExtRateLimit(permitsPerSecond = 1.0, timeOut = 500L, timeUnit = TimeUnit.MILLISECONDS)
    public String orderSkuByAuto() {
        if (order()) {
            System.out.println("恭喜您成功抢到该商品，耗时：");
        } else {
            System.out.println("抱歉，您未抢到该商品...");
        }

        return "success";
    }



    /**
     * 下单服务
     * @return
     */
    boolean order() {
        System.out.println("往订单表插入一条订单...");
        return true;
    }

}
