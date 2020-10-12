package com.huihui.aligo.mybatis;

import java.lang.reflect.Proxy;

/**
 * @author minghui.y
 * @create 2020-10-12 11:22
 **/
public class ExtSqlSession {

    /**
     * JDK动态代理获取代理Mapper
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getMapper(Class<T> clazz) {

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MybatisInvocationHandler());

    }
}
