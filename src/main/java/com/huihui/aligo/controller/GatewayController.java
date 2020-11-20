package com.huihui.aligo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 网关测试controller
 *
 * @author minghui.y
 * @create 2020-11-18 9:08 下午
 **/
@RestController
@RequestMapping("/gateway")
@CrossOrigin
public class GatewayController {

    @RequestMapping("/hello")
    public String gateway() {
        return "hello gateway!";
    }
}
