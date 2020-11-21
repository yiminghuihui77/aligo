package com.huihui.aligo.alibaba.sentinel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghui.y
 * @create 2020-11-21 1:49 下午
 **/
@RestController
@RequestMapping("/sentinel")
public class HelloSentinelController {


    @RequestMapping("/helloSentinel")
    public String helloSentinel() {
        return "hello sentinel!";
    }

}
