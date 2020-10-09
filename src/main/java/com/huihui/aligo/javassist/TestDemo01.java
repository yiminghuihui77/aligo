package com.huihui.aligo.javassist;

import javassist.*;

import java.io.IOException;

/**
 * 创建一个新的User类
 * @author minghui.y BG358486
 * @create 2020-10-03 13:14
 **/
public class TestDemo01 {

    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {

        ClassPool pool = ClassPool.getDefault();
        //创建类
        CtClass ctClass = pool.makeClass("com.huihui.aligo.User");
        //创建字段
        CtField nameField = CtField.make("private String name;", ctClass);
        CtField ageField = CtField.make("private Integer age;", ctClass);
        ctClass.addField(nameField);
        ctClass.addField(ageField);
        //创建方法
        CtMethod getNameMethod = CtMethod.make("public String getName() { return name; }", ctClass);
        CtMethod setAgeMethod = CtMethod.make("public void setAge(Integer age) { this.age = age; }", ctClass);
        ctClass.addMethod(getNameMethod);
        ctClass.addMethod(setAgeMethod);
        //构造方法
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{pool.get("java.lang.String"), CtClass.intType}, ctClass);
        ctClass.addConstructor(ctConstructor);

        //class文件写入文件
        ctClass.writeFile("C:\\Users\\minghui.y\\Desktop\\蚂蚁课堂工具");


    }



}
