package com.huihui.aligo.arithmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目：判断数组里每个元素，是否满足A = B + 2C
 * @author minghui.y
 * @create 2020-11-29 8:10 下午
 **/
public class TestDemo {

    public static void main(String[] args) {

        int size = 0;
        List<Integer> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            size = scanner.nextInt();
            if (size < 3 || size > 100) {
                System.out.println(0);
                return;
            }
        }
        for (int i = 0; i < size; i++) {
            int num = scanner.nextInt();
            if (num < 0 || num > 65535) {
                System.out.println(0);
                return;
            }
            list.add(num);
        }
        //数据准备完毕

        boolean isFind = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //先算出B + 2C是否存在于集合中
                if (list.get(i) == list.get(j)) {
                    //让C != B
                    continue;
                }

                int result = list.get(i) + 2 * list.get(j);

                if (list.contains(result) && result != list.get(i) && result != list.get(j) && list.get(i) != list.get(j)) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(result).append(" ").append(list.get(i)).append(" ").append(list.get(j));
                    System.out.println(builder.toString());
                    isFind = true;
                }

            }

        }
        if (!isFind) {
            System.out.println(0);
        }
    }
}
