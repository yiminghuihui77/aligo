package com.huihui.aligo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author minghui.y BG358486
 * @create 2020-09-26 15:19
 **/
public class ThreadPoolDemo {


    public static void main(String[] args) {
        //可缓存线程池
//        testCacheThreadPool();

        //固定线程数的线程池
//        testFixedThreadPool();

        //延迟执行的线程池
//        testScheduledThreadPool();

        //单个线程的线程池
        testSingleThreadPool();
    }

    public static void testSingleThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0;i < 10;i++) {
            executorService.execute(getTask(i));
        }
        //关闭线程池
        executorService.shutdown();
    }

    public static void testScheduledThreadPool() {
        ExecutorService executorService = Executors.newScheduledThreadPool(3);
        for (int i = 0;i < 10;i++) {
            ((ScheduledExecutorService) executorService).schedule(getTask(i), 3, TimeUnit.SECONDS);
        }
        //关闭线程池
        executorService.shutdown();
    }


    public static void testFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0;i < 10;i++) {
            executorService.execute(getTask(i));
        }
        //关闭线程池
        executorService.shutdown();
    }

    public static void testCacheThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0;i < 10;i++) {
            executorService.execute(getTask(i));
        }
        //关闭线程池
        executorService.shutdown();
    }

    private static Runnable getTask(int i) {
        Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + "执行：" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        };
        return task;
    }

}
