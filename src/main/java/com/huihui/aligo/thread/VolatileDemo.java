package com.huihui.aligo.thread;

/**
 * @author minghui.y BG358486
 * @create 2020-09-28 20:39
 **/
public class VolatileDemo {

    public static boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {

            int count = 0;
            while (running) {
                System.out.println(count ++);
            }

            System.out.println("子线程结束...");

        }).start();

        Thread.sleep(1000);

        running = false;

    }

}
