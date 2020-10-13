package com.huihui.aligo.collection;

import com.huihui.aligo.model.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义集合
 *
 * @author minghui.y
 * @create 2020-10-12 21:01
 **/
public class ExtArrayList {

    public static void main(String[] args) {
        Object[] objects = new Object[10];
        objects[0] = new Store();
        objects[1] = new Store();
        System.out.println(objects.length);

        List<Store> stores = new ArrayList<>(10);
        stores.add(new Store());
        stores.add(new Store());
        System.out.println(stores.size());

    }

}
