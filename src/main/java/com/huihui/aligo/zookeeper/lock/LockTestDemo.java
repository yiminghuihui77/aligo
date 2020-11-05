package com.huihui.aligo.zookeeper.lock;

/**
 * 测试分布式锁
 *
 * @author minghui.y BG358486
 * @create 2020-10-31 21:10
 **/
public class LockTestDemo {

    public static void main(String[] args) {

        for (int i = 0;i < 100; i++) {
            new Thread(new Task()).start();
        }


    }


    public static class Task implements Runnable {
        /**
         * 每个任务都有自己的锁
         */
        private DistributionZookeeperLock lock = new DistributionZookeeperLock();

        @Override
        public void run() {

            try {
                lock.lock();

                System.out.println(Thread.currentThread().getName() + "线程获得了锁...");
                Thread.sleep(1000 * 2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "线程释放了锁...");
                lock.unLock();
            }

        }
    }
}
