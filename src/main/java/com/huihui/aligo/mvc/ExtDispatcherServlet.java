package com.huihui.aligo.mvc;

import com.huihui.aligo.annotation.ExtAutowired;
import com.huihui.aligo.annotation.ExtComponent;
import com.huihui.aligo.annotation.ExtController;
import com.huihui.aligo.annotation.ExtRequestMapping;
import com.huihui.aligo.ioc.utils.ClassUtils;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义DispatcherServlet
 *  SpringBoot项目没有web.xml，使用@WebServlet注解Servlet，拦截所有请求/
 * @author minghui.y
 * @create 2020-10-09 15:55
 **/
//@WebServlet(name = "extDispatcherServlet", urlPatterns = "/mvc")
public class ExtDispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtDispatcherServlet.class);

    /**
     * IOC容器：key为beanId，value为bean实例
     */
    private final Map<String, Object> beanMap = new ConcurrentHashMap<>(8);
    /**
     * key为请求url，value为Method
     */
    private final Map<String, Method> urlMethodMap = new ConcurrentHashMap<>(8);
    /**
     * key为请求url，value为bean实例
     */
    private final Map<String, Object> urlBeanMap = new ConcurrentHashMap<>(8);
    /**
     * 能注入到IOC容器的注解
     */
    private final List<Class<?>> beanAnnotations = Arrays.asList(ExtComponent.class, ExtController.class);

    /**
     * 默认扫包路径（待优化）
     */
    private static final String PACKAGE_SCAN_PATH = "com.huihui.aligo";


    /**
     * Servlet初始化
     */
    @Override
    public void init() {
        LOGGER.info("ExtDispatcherServlet start init...");

        try {
            //1、初始化IOC容器
            initIoc();

            //2、HandlerMapping：遍历IOC容器中的bean,获取其Class，获取所有被@ExtRequestMapping注解的方法
            handlerMapping();


        } catch (Exception e) {
            LOGGER.error("ExtDispatcherServlet初始化异常...");
            e.printStackTrace();
        }

    }

    /**
     * 处理器URL关系映射
     */
    private void handlerMapping() {
        //从IOC容器中遍历所有bean，处理@ExtRequestMapping注解
        for (Object bean : beanMap.values()) {

            Class<?> clazz = bean.getClass();
            //处理类上的@ExtRequestMapping注解
            ExtRequestMapping extRequestMapping = clazz.getAnnotation(ExtRequestMapping.class);
            String baseUrl = StringUtils.EMPTY;
            if (extRequestMapping != null) {
                baseUrl = extRequestMapping.value();
            }

            //遍历所有Method
            for (Method method : clazz.getDeclaredMethods()) {
                ExtRequestMapping methodRequestMapping = method.getAnnotation(ExtRequestMapping.class);
                if (methodRequestMapping != null) {
                    String methodUrl = baseUrl + methodRequestMapping.value();
                    //存入map中(待优化：若URL重复，直接覆盖不太友好)
                    urlMethodMap.put(methodUrl, method);
                    urlBeanMap.put(methodUrl, bean);
                }
            }



        }
    }

    /**
     * 获取类上所有注解的Class
     * @param clazz
     * @return
     */
    private List<Class<?>> getClassAnnotations(Class<?> clazz) {
        List<Class<?>> classList = new ArrayList<>();
        for (Annotation annotation : clazz.getAnnotations()) {
            classList.add(annotation.annotationType());
        }
        return classList;
    }

    /**
     * 初始化IOC容器
     * 注册bean + 依赖注入
     * @throws Exception
     */
    private void initIoc() throws Exception {
        //扫包获取所有Class
        List<Class<?>> classList = ClassUtils.getClasses(PACKAGE_SCAN_PATH);
        //遍历Class，获取bean存入IOC
        for (Class<?> clazz : classList) {
            List<Class<?>> currentAnnotations = getClassAnnotations(clazz);
            Boolean isNotBean = Collections.disjoint(beanAnnotations, currentAnnotations);
            //当前类没有被@ExtController或@ExtComponent注解
            if (isNotBean) {
                continue;
            }
            //生成默认的beanId
            String beanId = ClassUtils.generateBeanId(clazz);
            Object bean = clazz.newInstance();
            beanMap.put(beanId, bean);
        }
        //依赖注入
        for (Object bean : beanMap.values()) {
            Class<?> clazz = bean.getClass();
            //获取所有Field，若被@ExtAutowired注解，则依赖注入
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ExtAutowired extAutowired = field.getAnnotation(ExtAutowired.class);
                if (extAutowired != null) {
                    //从IOC容器中获取bean给field注入
                    Class<?> fieldClass = field.getType();
                    field.setAccessible(true);
                    field.set(bean, beanMap.get(ClassUtils.generateBeanId(fieldClass)));
                }
            }
        }
    }

    /**
     * 处理POST请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            //获取请求URL
            String url = req.getRequestURI();

            Method method = urlMethodMap.get(url);
            if (method == null) {
                resp.getWriter().println("url error for method: {404}");
            }

            Object bean = urlBeanMap.get(url);
            if (bean == null) {
                resp.getWriter().println("url error for bean: {404}");
            }

            //反射调用method
            String result = (String) method.invoke(bean);

            //调用视图解析器处理
            viewResolver(result, req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("post请求完成...");
    }

    /**
     * 视图解析处理
     * @param result
     * @param req
     * @param resp
     */
    private void viewResolver(String result, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //后缀地址
        String suffix = ".jsp";
        //目录地址
        String prefix = "/";
        //请求转发（浏览器地址栏不变）
        req.getRequestDispatcher(prefix + result + suffix).forward(req, resp);
    }

    /**
     * 处理GET请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    /**
     * 销毁Servlet
     */
    @Override
    public void destroy() {
        super.destroy();
    }
}
