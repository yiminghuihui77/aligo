package com.huihui.aligo.genericity;

import java.util.ArrayList;

/**
 * 这是一个泛型类
 * 初始化时机：1、创建对象  2、子类继承该类
 *
 * @author minghui.y BG358486
 * @create 2020-09-13 22:11
 **/
public class GenericityClass<T> {

    private T info;

    /**
     * 泛型方法
     * @param <M>
     * @return
     */
        public <M> M get(ArrayList<? extends T> list) {
        return null;
    }
}
