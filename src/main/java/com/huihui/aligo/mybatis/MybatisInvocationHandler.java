package com.huihui.aligo.mybatis;

import com.huihui.aligo.annotation.ExtInsert;
import com.huihui.aligo.annotation.ExtParam;
import com.huihui.aligo.jdbc.JdbcUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JDK动态代理
 *
 * @author minghui.y
 * @create 2020-10-12 11:22
 **/
public class MybatisInvocationHandler implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        ExtInsert extInsert = method.getAnnotation(ExtInsert.class);
        if (extInsert != null) {
            return handInsert(method, args);
        }

        return null;
    }


    /**
     * 处理@ExtInsert注解
     * @param method
     * @param args
     * @return
     */
    private int handInsert(Method method, Object[] args) {
        //获取方法上的注解，获取SQL
        ExtInsert extInsert = method.getAnnotation(ExtInsert.class);
        if (extInsert == null) {
            throw new RuntimeException("Mapper 方法上没有配置注解，执行失败");
        }
        //原始的SQL
        String rawSql = extInsert.value();
        Map<String, Object> paramMap = new ConcurrentHashMap<>();

        //获取方法的参数，参数注解
        Parameter[] parameters = method.getParameters();
        for (int i = 0;i < parameters.length;i++) {
            ExtParam extParam = parameters[i].getAnnotation(ExtParam.class);
            if (extParam == null) {
                throw new RuntimeException("Mapper 方法的参数必须使用@ExtParam注解");
            }
            paramMap.put(extParam.value(), args[i]);

        }

        //处理原始SQL，#{xxx}替换成?
        String executableSql = rawSql.replaceAll(JdbcUtils.PARAM_REPLACE_REGEX, JdbcUtils.PARAM_REPLACE_TARGET);


        //获取原始SQL中的参数顺序
        List<Object> paramList = getParamList(rawSql, paramMap);

        //执行sql
        return JdbcUtils.insert(executableSql, paramList.toArray());
    }

    /**
     * 按照参数在SQL中的顺序，获取参数值集合
     * @param rawSQL
     * @param paramMap
     * @return
     */
    private List<Object> getParamList(String rawSQL, Map<String, Object> paramMap) {
        List<Object> paramList = new ArrayList<>();
        rawSQL = rawSQL.trim();
        int start = rawSQL.indexOf("values(") + 7;
        int end = rawSQL.indexOf("})") + 1;
        String temp = rawSQL.substring(start, end).replace("#{", "").replace("}", "");
        String[] tempParams = temp.split(",");
        for (String param : tempParams) {
            paramList.add(paramMap.get(param.trim()));
        }
        return paramList;
    }



}
