package com.huihui.aligo.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列demo
 *
 * @author minghui.y BG358486
 * @create 2020-09-26 14:05
 **/
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();

        Provider provider = new Provider(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        provider.start();

        Thread.sleep(2000);

        consumer.start();

        Thread.sleep(5000);
        provider.stopProvide();
    }


    /**
     * 生产者
     */
    public static class Provider extends Thread {
        private BlockingQueue<String> blockingQueue;
        private volatile boolean flag = true;
        private AtomicInteger count = new AtomicInteger(0);

        public Provider(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void stopProvide() {
            this.flag = false;
        }

        @Override
        public void run() {

            while (flag) {
                try {
                    String temp ="元素" + count.incrementAndGet();
                    boolean result = blockingQueue.offer(temp, 4, TimeUnit.SECONDS);
                    if (result) {
                        System.out.println("生产者入队列操作成功：" + temp);
                        Thread.sleep(2000);
                    } else {
                        System.out.println("生产者入队列操作失败：" + temp);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生产者停止入队操作...");
        }
    }


    /**
     * 消费者
     */
    public static class Consumer extends Thread {
        private BlockingQueue<String> blockingQueue;
        private boolean flag = true;

        public Consumer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {

            while (flag) {
               try {
                   String temp = blockingQueue.poll(2, TimeUnit.SECONDS);
                   if (temp == null) {
                       System.out.println("消费者超时未获取到队列元素");
                       flag = false;
                   } else {
                       System.out.println("消费者获取队列元素：" + temp);
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }

            }
            System.out.println("消费者停止消费...");
        }
    }


}
