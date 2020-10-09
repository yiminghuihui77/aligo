package com.huihui.aligo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author minghui.y BG358486
 * @create 2020-10-03 10:09
 **/
@RestController
@RequestMapping("/jmeter")
public class JmeterController {

    public static int count;
    public static List<Object> buffer = new ArrayList<>();


    @RequestMapping(value = "/throughput", method = RequestMethod.GET)
    public String testThroughput() {
        System.out.println("当前访问第" + ++count + "次");
//        byte[] temp = new byte[1 * 1024 * 1024];
//        buffer.add(temp);
        return String.valueOf(count);
    }
}
