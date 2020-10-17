package com.huihui.aligo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author minghui.y BG358486
 * @create 2020-10-17 16:07
 **/
@Controller
@RequestMapping("hello")
class HelloWorldController {

    @RequestMapping(value = "sayHello", method = RequestMethod.GET)
    public String sayHello(Map<String, Object> map) {
//        int i = 1 / 0;

        map.put("name", "helloWorld");
        return "home";
    }

}
