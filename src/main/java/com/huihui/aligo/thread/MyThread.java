package com.huihui.aligo.thread;

/**
 * 复习多线程知识
 *
 * @author minghui.y BG358486
 * @create 2020-09-08 21:33
 **/
public class MyThread {
    /**
     * 循环次数足够大才能看出多线程效果
     */
    private static final Integer LOOP_TIMES = 1000;

    public static void main(String[] args) {

        //启动新线程
        new Thread(new MyTask()).start();

        //主线程的任务
        for (int i = 0;i < LOOP_TIMES;i ++) {
            System.out.println(Thread.currentThread().getName() + "线程正在执行第" + i +"次循环");
        }

    }

    public static class MyTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0;i < LOOP_TIMES;i ++) {
                System.out.println(Thread.currentThread().getName() + "线程正在执行第" + i +"次循环");
            }
        }
    }
}
