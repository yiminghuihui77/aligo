package com.huihui.aligo.arithmetic;

import java.io.IOException;
import java.util.*;

/**
 * @author minghui.y
 * @create 2020-11-29 9:11 下午
 **/
public class IntegerSortDemo {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int size = 0;
        List<Integer> list = new ArrayList<>();
        if (scanner.hasNextLine()) {
            String readLine = scanner.nextLine();
            String[] chars = readLine.split(",");
            size = chars.length;
            if (size < 1 || size > 1000) {
                System.out.println("数组长度错误");
                return;
            }

            for (int i =0;i < size; i++) {
                list.add(Integer.valueOf(chars[i].trim()));
            }
        }




        //数据准备完毕



        List<Temp> temps = new ArrayList<>();
        for (Integer num : list) {
            temps.add(new Temp(num));
        }

        Collections.sort(temps);
        StringBuilder builder = new StringBuilder();
        for (Temp temp : temps) {
            builder.append(temp.getSource()).append(" ");
        }
        System.out.println(builder.toString());



    }


    /**
     * 获取个位数
     * @param source
     * @return
     */
    public static int getGeOfSource(int source) {
        char[] buffer = String.valueOf(source).toCharArray();
        return Integer.valueOf(buffer[buffer.length-1] + "");
    }


    public static class Temp implements Comparable<Temp> {

        private int source;
        private int ge;

        public int getSource() {
            return source;
        }

        public Temp(int source) {
            this.source = source;
            this.ge = getGeOfSource(source);
        }

        @Override
        public int compareTo(Temp o) {
            return ge - o.ge;
        }
    }

}
