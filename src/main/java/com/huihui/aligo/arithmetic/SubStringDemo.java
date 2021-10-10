package com.huihui.aligo.arithmetic;

import java.util.Scanner;

/**
 * @author minghui.y
 * @create 2020-11-29 10:10 下午
 **/
public class SubStringDemo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String bufferA = scanner.nextLine();
        String bufferB = scanner.nextLine();
        int v = scanner.nextInt();


        int size = bufferA.length();

        String[] str = new String[10];
        int n = 0;
        for (int i = 0;i < size;i++) {
            for (int j = 0;j< size; i++) {
                if (bufferA.indexOf(bufferB.substring(i, j)) != -1 && Math.abs(bufferA.charAt(i) - bufferB.charAt(i)) <= v) {
                    str[n] = bufferB.substring(i, j);
                    n++;
                }

            }
        }

        int maxLength = str[0].length();

        for (int i = 0; i< n; i++) {
            if (str[i].length() > maxLength) {
                maxLength = str[i].length();
            }
        }

        System.out.println(maxLength);

    }
}
