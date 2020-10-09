package com.huihui.aligo.javassist;

import com.huihui.aligo.model.Store;
import javassist.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改一个已有的类，添加新的方法
 * @author minghui.y BG358486
 * @create 2020-10-03 14:12
 **/
public class TestDemo02 {

    public static final String SET = "set";
    public static final String GET = "get";

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {

        List<String> setList = new ArrayList<>();
        List<String> getList = new ArrayList<>();


        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.huihui.aligo.model.Store");

        //获取所有字段，拼接方法
        for (int i = 0;i < ctClass.getDeclaredFields().length; i++) {
            CtField field= ctClass.getDeclaredFields()[i];
            setList.add(SET + captureName(field.getName()));
            getList.add(GET + captureName(field.getName()));

            String setMethodName = SET + captureName(field.getName());
            String getMethodName = GET + captureName(field.getName());

            //创建方法方式一
            CtMethod getMethod = CtMethod.make("public String " + getMethodName + "() { return " + field.getName() + "; }", ctClass);
            ctClass.addMethod(getMethod);

            //创建方法方式二
            CtMethod setMethod = new CtMethod(CtClass.voidType, setMethodName, new CtClass[]{pool.get("java.lang.String")}, ctClass);
            setMethod.setModifiers(Modifier.PUBLIC);
            //$1表示第一个参数、$0表示this参数
            setMethod.setBody("{ this." + field.getName() + " = $1; }");
            ctClass.addMethod(setMethod);

        }

        //写入到 文件
        ctClass.writeFile("C:\\Users\\minghui.y\\Desktop\\蚂蚁课堂工具");

        Class<?> clazz = ctClass.toClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0;i < methods.length;i++) {
            Method method = methods[i];
            System.out.println(method.getName());

        }







    }


    /**
     * 字符串首字母大写
     * @param name
     * @return
     */
    public static String captureName(String name) {
//        name = name.substring(0, 1).toUpperCase() + name.substring(1);
//        return  name;
        char[] cs=name.toCharArray();
        //ascii表中，小写字母-大写字母 = 32
        cs[0]-=32;
        return String.valueOf(cs);

    }
}
