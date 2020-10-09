package com.huihui.aligo.thread;

/**
 * 死锁demo
 *
 * @author minghui.y BG358486
 * @create 2020-10-03 11:56
 **/
public class DeadLockDemo {

    public static void main(String[] args) {

        Task task = new Task();
        new Thread(task, "线程1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.flag = false;

        new Thread(task, "线程2").start();


    }


    public static class Task implements Runnable {

        private Object objA = new Object();
        private Object objB = new Object();
        private volatile boolean flag = true;


        @Override
        public void run() {

            if (flag) {
                synchronized (objA) {
                    System.out.println(Thread.currentThread().getName() + "获取objA锁...");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (objB) {
                        System.out.println(Thread.currentThread().getName() + "获取objB锁...");
                    }
                }
            } else {
                synchronized (objB) {
                    System.out.println(Thread.currentThread().getName() + "获取objB锁...");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (objA) {
                        System.out.println(Thread.currentThread().getName() + "获取objA锁...");
                    }

                }
            }

        }
    }

}
