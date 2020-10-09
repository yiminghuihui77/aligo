package com.huihui.aligo.controller;

import com.huihui.aligo.annotation.ExtAutowired;
import com.huihui.aligo.annotation.ExtController;
import com.huihui.aligo.annotation.ExtRequestMapping;
import com.huihui.aligo.model.Foot;

/**
 * 手写SpringMVC框架测试类
 *
 * @author minghui.y
 * @create 2020-10-09 17:19
 **/
@ExtController
@ExtRequestMapping(value = "/mvc")
public class ExtMvcController {

    @ExtAutowired
    private Foot foot;

    @ExtRequestMapping(value = "/testMvc")
    public String testMvc() {
        foot.walk();
        return "ExtMvc success";
    }

}
