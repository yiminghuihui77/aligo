package com.huihui.aligo.reflect;

import com.huihui.aligo.model.Store;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射机制练习
 *
 * @author minghui.y BG358486
 * @create 2020-10-05 12:37
 **/
public class ReflectDemo001 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //获取Class对象的三种方式
        //方式一：
        Class clazz1 = Class.forName("com.huihui.aligo.model.Store");

        //方式二：
        Class clazz2 = Store.class;

        //方式三：
//        Class clazz3 = new Store().getClass();

        //实例化的方式
//        Store store1 = (Store) clazz1.newInstance();

        Constructor constructor = clazz2.getDeclaredConstructor();
        constructor.setAccessible(true);
        Store store2 = (Store) constructor.newInstance();

//        Constructor constructor2 = clazz3.getConstructor(String.class, String.class, String.class);
//        Store store3 = (Store) constructor2.newInstance("17826789899", "灰灰店铺", "浙江省杭州市");

       Field[] fields = clazz2.getDeclaredFields();
       Field field = fields[0];
       field.setAccessible(true);
       field.set(store2, "222");

        System.out.println(store2);




    }
}
