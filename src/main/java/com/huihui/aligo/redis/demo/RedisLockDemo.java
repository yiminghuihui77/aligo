package com.huihui.aligo.redis.demo;

import com.huihui.aligo.redis.lock.RedisLockService;

/**
 * @author minghui.y
 * @create 2020-11-12 10:33 下午
 **/
public class RedisLockDemo {

    public static void main(String[] args) {
        Task task = new Task();

        for (int i = 1;i < 50;i++) {
            new Thread(task).start();
        }


    }


    /**
     * 任务：获取锁后休眠两秒，然后释放锁
     */
    public static class Task implements Runnable {

        @Override
        public void run() {
            if (RedisLockService.getInstance().getLock().lock(5, 3)) {
                System.out.println(Thread.currentThread().getName() + "线程成功获取了锁...");

                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //释放锁
                RedisLockService.getInstance().getLock().unLock();
                System.out.println(Thread.currentThread().getName() + "线程释放了锁~~~");
            } else {
                System.out.println(Thread.currentThread().getName() + "线程获取锁失败！！！");
            }
        }
    }
}
