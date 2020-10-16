package com.huihui.aligo.collection;

/**
 * @author minghui.y
 * @create 2020-10-15 20:29
 **/
public class HashMapDemo {

    public static void main(String[] args) {
        ExtHashMap<String, String> map = new ExtHashMap<>();

        map.put("1", "张三");
        map.put("2", "李四");
        //追加在2后
        map.put("13", "小西瓜");

        map.put("3", "王五");
        map.put("4", "赵六");
        map.put("5", "田七");

        map.printMap();
        System.out.println("--------我是无情的分割线----------");
        //测试覆盖
        map.put("4", "赵六六六");
        map.printMap();
        System.out.println("--------我是无情的分割线----------");

        //测试get方法
//        System.out.println(map.get("4"));

        //测试扩容
        map.put("7", "晓明");
        map.put("8", "小东");
        System.out.println("扩容后：");
        map.printMap();

        //存储key为null的情况
        System.out.println("--------我是无情的分割线----------");
        map.put(null, "key是null");
        map.printMap();
    }

}
