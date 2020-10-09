package com.huihui.aligo.ioc;

import com.huihui.aligo.model.Store;
import com.huihui.aligo.model.User;

/**
 * @author minghui.y BG358486
 * @create 2020-10-07 22:45
 **/
public class ApplicationContextDemo {

    public static void main(String[] args) throws Exception {

        //手写XML版本IOC容器
//        testXmlContext();

        //手写注解版IOC容器
        testAnnotationContext();

    }


    public static void testXmlContext() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("MySpring.xml");

        Store store = (Store) context.getBeanById("store");
        store.sale();

        User user = (User) context.getBeanByClass(User.class);
        user.talk();
    }

    public static void testAnnotationContext() throws InstantiationException, IllegalAccessException {
        AnnotationApplicationContext context = new AnnotationApplicationContext("com.huihui.aligo");

        Store store = (Store) context.getBeanById("store");
        store.sale();

        User user = (User) context.getBeanById("user");
        user.talk();
        //依赖注入测试
        user.walk();
    }

}
