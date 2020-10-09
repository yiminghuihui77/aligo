package com.huihui.aligo.ioc;

import com.huihui.aligo.annotation.ExtAutowire;
import com.huihui.aligo.annotation.ExtComponent;
import com.huihui.aligo.ioc.utils.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注解版SpringIOC容器
 * 缺点：仅支持单例、
 *
 * @author minghui.y BG358486
 * @create 2020-10-08 10:55
 **/
public class AnnotationApplicationContext {

    /**
     * 扫描范围
     */
    private final String packagePath;

    private ConcurrentHashMap<String, Object> beanMap;

    /**
     * 构造方法：初始化容器
     * @param packagePath
     */
    public AnnotationApplicationContext(String packagePath) throws InstantiationException, IllegalAccessException {
        if (StringUtils.isEmpty(packagePath)) {
            throw new RuntimeException("无效扫包路径");
        }
        this.packagePath = packagePath;
        //1、扫描包路径，获取包下所有Class对象
        List<Class<?>> classList = ClassUtils.getClasses(packagePath);
        scanClass4Annotation(classList);
        if (CollectionUtils.isEmpty(beanMap)) {
            throw new RuntimeException("包路径下无被注解的Bean");
        }
        //给IOC容器中的bean依赖注入
        injectDependency();

    }


    /**
     * 根据beanId获取实例
     * @param beanId
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Object getBeanById(String beanId) {
      return beanMap.get(beanId);
    }

    /**
     * 1、遍历Class集合，获取被@ExtComponent注解的Class，实例化后注册到IOC容器
     * @param classList
     * @return
     */
    private ConcurrentHashMap<String, Object> scanClass4Annotation(List<Class<?>> classList) throws IllegalAccessException, InstantiationException {
        beanMap = new ConcurrentHashMap<>(8);

        for (Class<?> clazz : classList) {
            //反射获取类上的注解
            ExtComponent extComponent = clazz.getAnnotation(ExtComponent.class);
            if (extComponent == null) {
                continue;
            }
            //生成beanId和实例，存入map
            Object object = clazz.newInstance();
            beanMap.put(ClassUtils.generateBeanId(clazz), object);

        }

        return beanMap;
    }

    /**
     * 依赖注入
     * @param clazz
     * @param object
     */
    private void assign4Bean(Class<?> clazz, Object object) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //判断属性是否被@ExtAutowire注解
            ExtAutowire extAutowire = field.getAnnotation(ExtAutowire.class);
            if (extAutowire != null) {
                //从IOC容器中查询Filed的bean
                String fieldBeanId = ClassUtils.generateBeanId(field.getType());
                Object fieldBean = beanMap.get(fieldBeanId);
                if (fieldBean == null) {
                    throw new RuntimeException("依赖注入失败，目标bean未被注册到IOC容器中");
                }
                field.setAccessible(true);
                field.set(object, fieldBean);
            }
        }
    }

    /**
     * 给IOC容器中的所有Bean依赖注入
     */
    private void injectDependency() throws IllegalAccessException {
        Collection<Object> objects = beanMap.values();
        for (Object object : objects) {
            assign4Bean(object.getClass(), object);
        }
    }


}
