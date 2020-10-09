package com.huihui.aligo.io;

import java.io.UnsupportedEncodingException;

/**
 * 字符编码问题
 * @author minghui.y BG358486
 * @create 2020-09-12 19:15
 **/
class UnicodeDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //打印所有环境变量
        System.getProperties().list(System.out);


        String temp = "大家一起学java！";
        byte[] buffer = temp.getBytes("ISO8859-1");

        String result = new String(buffer, "GBK");
        System.out.println(result);
    }
}
