package com.huihui.aligo.genericity;

import com.huihui.aligo.model.Store;

/**
 * @author minghui.y BG358486
 * @create 2020-09-13 22:18
 **/
public interface GenericityInterface<T extends Store> {

    T getSomething();
}
