package com.huihui.aligo.thread;

/**
 * 了解后台线程
 *
 * 知识点：1、对于JVM进程来说，只有有一个前台线程在运行，进程就不会结束
 *        2、只有后台线程在运行，进程就会结束
 *
 * @author minghui.y BG358486
 * @create 2020-09-08 22:12
 **/
public class DaemonThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyTask());
        //在调用.start()方法前调用，设置为后台线程
        //通过注释下面一行来观察区别
//        thread.setDaemon(true);


        thread.start();

        System.out.println("main线程正在执行...");

        thread.interrupt();
    }

    public static class MyTask implements Runnable {

        private int times;
        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ": 后台线程正在执行:" + times++);
            }
        }
    }
}
