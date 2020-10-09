package com.huihui.aligo.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author minghui.y BG358486
 * @create 2020-09-26 20:35
 **/
public class ThreadPoolDemo2 {

    public static void main(String[] args) {

        //任务队列
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(3);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, queue);

        //创建新线程
        executor.execute(new Task("一号任务", queue));
        //进入队列
        executor.execute(new Task("二号任务",queue));
        executor.execute(new Task("三号任务",queue));
        executor.execute(new Task("四号任务",queue));
        //创建新线程
        executor.execute(new Task("五号任务",queue));
        //报错
        executor.execute(new Task("六号任务",queue));


    }


    public static class Task implements Runnable {
        private String name;
        private BlockingQueue<Runnable> queue;

        public Task(String name, BlockingQueue<Runnable> queue) {
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": " + name + "  队列大小：" + queue.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
