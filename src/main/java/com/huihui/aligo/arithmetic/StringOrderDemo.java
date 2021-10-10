package com.huihui.aligo.arithmetic;

import java.util.Scanner;

/**
 * 字符串排序demo
 * 题目：
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 * 如，输入： Type 输出： epTy
 *
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 * 如，输入： BabA 输出： aABb
 *
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 * 如，输入： By?e 输出： Be?y
 * @author minghui.y
 * @create 2020-11-24 7:25 下午
 **/
public class StringOrderDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }



    }


    /**
     * 真正处理的逻辑
     * @param temp
     */
    public static void doSomething(String temp) {
        //将字符串拆分成字符，只将字符存储到List集合中

        //字符集合先全部设置为小写，然后排序

        //遍历原始的字符串，保证字母
    }


}
