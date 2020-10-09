package com.huihui.aligo;

import java.io.*;

/**
 * @author minghui.y BG358486
 * @create 2020-09-05 20:22
 **/
public class Basic {

  private int age;

  public Basic() {
      System.out.println("无参构造方法");
  }

  public Basic(int age) {
//      this();
      super();
      System.out.println("有参构造方法");
      this.age = age;

  }

  public void talk() throws Exception {
  }

  public void say() {
      try {
          this.talk();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

    public static void main(String[] args) throws Exception {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(is);

        String input;
        System.out.println("请输入一行信息");

        while ((input = reader.readLine()) != null && input.length() >0) {
            System.out.println("您输入的内容为：" + input);
            System.out.println("请输入一行信息");
        }

    }


}
