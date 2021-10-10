package com.huihui.aligo.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * 三个线程分别打印a、l、1以此类推
 *  * 两个核心问题：
 *  * 1、每个线程需要打印特定的序号，因此每个线程的任务是存在差异的
 *  * 2、要想实现序号的顺序打印，需要上锁，那就需要任务是一个共享的资源
 */
public class AlibabaThreadDemo {

    private static volatile  int count;

    public static void main(String[] args) {
        Task task = new Task();
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();


    }

    public static class Task implements Runnable {

        private Map<String, String> map = new HashMap<>(3);
        private Map<String, Integer> map2 = new HashMap<>(3);

        public Task() {
            map.put("Thread-0", "a");
            map.put("Thread-1", "l");
            map.put("Thread-2", "i");

            map2.put("Thread-0", 0);
            map2.put("Thread-1", 1);
            map2.put("Thread-2", 2);
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {

                    if (count % 3 == map2.get(Thread.currentThread().getName()) ) {

                        System.out.println(Thread.currentThread().getName() + "打印：" + map.get(Thread.currentThread().getName()));
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        count++;
                    }


                }
                }
            }
        }


}
