package com.huihui.aligo.ioc;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 手写SpringIOC容器：XML版
 * 缺点：多例，没有容器（ConcurrentHashMap）存储Bean、每次调用getBeanByXxx就会反射出一个实例
 *
 * @author minghui.y BG358486
 * @create 2020-10-07 22:22
 **/
public class ClassPathXmlApplicationContext {

    private static final String ATTRIBUTE_BEAN_ID = "id";
    private static final String ATTRIBUTE_BEAN_CLASS = "class";
    private static final String ATTRIBUTE_PROPERTY_NAME = "name";
    private static final String ATTRIBUTE_PROPERTY_VALUE = "value";

    /**
     * xml文件路径
     */
    private final String xmlPath;

    public ClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }


    /**
     * 通过beanId获取实例
     * @param beanId
     * @return
     */
    public Object getBeanById(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)) {
            throw new RuntimeException("无效beanId");
        }
        //1、解析XML文件，获取子节点下的所有<ibean>节点
        List<Element> elements = getElementsByXmlPath();

        //2、遍历elements集合，获取域beanId一致的element
        for (Element element : elements) {
            String elementBeanId = element.attributeValue(ATTRIBUTE_BEAN_ID);
            if (beanId.equals(elementBeanId)) {
                //3、找到目标element，使用反射机制，实例化一个Object
                String classPath = element.attributeValue(ATTRIBUTE_BEAN_CLASS);
                return reflectByClassPath(classPath, element);
            }

        }

        return null;
    }

    /**
     * 根据Class对象获取bean
     * @param clazz
     * @return
     */
    public Object getBeanByClass(Class<?> clazz) throws Exception {
        if (clazz == null) {
            throw new RuntimeException("无效Class对象");
        }
        //获取Class的全限定名称
        String classPath = clazz.getName();

        //1、解析XML文件，获取子节点下的所有<ibean>节点
        List<Element> elements = getElementsByXmlPath();

        //2、遍历elements集合，获取域beanId一致的element
        for (Element element : elements) {
            String elementBeanId = element.attributeValue(ATTRIBUTE_BEAN_CLASS);
            if (classPath.equals(elementBeanId)) {
                return reflectByClassPath(classPath, element);
            }

        }

        return null;
    }

    /**
     * 根据全限定名反射一个实例
     * @param classPath
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object reflectByClassPath(String classPath, Element element) throws Exception {
        if (StringUtils.isEmpty(classPath)) {
            throw new RuntimeException("无效class全称路径");
        }
        //获取Class对象
        Class<?> clazz = Class.forName(classPath);
        Object object = clazz.newInstance();

        //获取对象的属性
        Iterator<Element> iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element property = iterator.next();
            Field field = clazz.getDeclaredField(property.attributeValue(ATTRIBUTE_PROPERTY_NAME));
            if (field != null) {
                field.setAccessible(true);
                //获取字段的类型
                Class<?> filedClazz = field.getType();
                field.set(object, transformValue(filedClazz, property.attributeValue(ATTRIBUTE_PROPERTY_VALUE)));
            }
        }

        return object;
    }

    /**
     * 根据xml获取所有bean节点的element
     * @return
     * @throws DocumentException
     */
    private List<Element> getElementsByXmlPath() throws DocumentException {
        if (StringUtils.isEmpty(xmlPath)) {
            throw new RuntimeException("无效xml路径");
        }
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        Document document = new SAXReader().read(is);
        //获取根节点
        Element root = document.getRootElement();

        return root.elements();
    }



    /**
     * 将value转换为对应的class类型 fieldType
     *
     * @param fieldType class类型
     * @param value     需要转换的值
     * @return Object
     * @throws Exception 反射获取类 Class.forName 可能会导致异常
     */
    private Object transformValue(Class fieldType, String value) throws Exception {
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        if (fieldType == String.class) {
            return String.valueOf(value);
        } else if (fieldType == Boolean.class || fieldType == boolean.class) {
            return Boolean.valueOf(value);
        } else if (fieldType == Byte.class || fieldType == byte.class) {
            return Byte.valueOf(value);
        } else if (fieldType == Double.class || fieldType == double.class) {
            return Double.valueOf(value);
        } else if (fieldType == Float.class || fieldType == float.class) {
            return Float.valueOf(value);
        } else if (fieldType == Integer.class || fieldType == int.class) {
            return Integer.valueOf(value);
        } else if (fieldType == Long.class || fieldType == long.class) {
            return Long.valueOf(value);
        } else if (fieldType == Short.class || fieldType == short.class) {
            return Short.valueOf(value);
        } else if (fieldType == Character.class || fieldType == char.class) {
            return value.charAt(0);
        } else if (fieldType == BigDecimal.class) {
            return new BigDecimal(value);
        } else if (fieldType == BigInteger.class) {
            return new BigInteger(value);
        } else if (fieldType == Date.class) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(value);
        } else if (fieldType == List.class) {
            return Arrays.asList(value.split(","));
        } else if (fieldType == Set.class) {
            return new HashSet<>(Arrays.asList(value.split(",")));
        } else if (fieldType.isEnum()) {
            // 枚举类型
            Class<?> cl = Class.forName(fieldType.getName());
            Field field = cl.getDeclaredField(value);
            return field.get(cl);
        } else if (fieldType == Pattern.class) {
            return Pattern.compile(value);
        } else {
            return value;
        }
    }

}
