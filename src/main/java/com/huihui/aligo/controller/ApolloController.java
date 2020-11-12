package com.huihui.aligo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apollo")
public class ApolloController {

    @Value("${zhangsan:zhangsan}")
    private String data;

    @RequestMapping(value = "/getConfigInfo", method = RequestMethod.GET)
    public String getConfig() {
        return data;
    }
}
