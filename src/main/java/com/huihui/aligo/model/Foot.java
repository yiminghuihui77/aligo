package com.huihui.aligo.model;

import com.huihui.aligo.annotation.ExtComponent;

/**
 * 脚
 * 用于测试依赖注入功能
 *
 * @author minghui.y BG358486
 * @create 2020-10-08 15:46
 **/
@ExtComponent
public class Foot {

    public void walk() {
        System.out.println("I can Walk...");
    }
}
