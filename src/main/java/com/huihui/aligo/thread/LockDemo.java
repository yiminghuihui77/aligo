package com.huihui.aligo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author minghui.y BG358486
 * @create 2020-09-26 10:48
 **/
public class LockDemo {

    public static void main(String[] args) {
        Human human = new Human();
        Condition condition = human.lock.newCondition();

        Provider provider = new Provider(human, condition);
        Consumer consumer = new Consumer(human, condition);
        provider.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumer.start();


    }

    /**
     * 共享资源
     */
    public static class Human {
        private String name;
        private String sex;
        private boolean isWrited = false;
        private Lock lock = new ReentrantLock(true);

    }

    /**
     * 生产者
     */
    public static class Provider extends Thread {

        private Human human;
        private Condition condition;

        public Provider(Human human, Condition condition) {
            this.human = human;
            this.condition = condition;
        }

        @Override
        public void run() {
            int count = 0;

            while (true) {
                //生产前锁定资源
                human.lock.lock();

                if (human.isWrited) {
                    //被写入过，则等待
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (count == 0) {
                        human.name = "张三";
                        human.sex = "男";
                    } else {
                        human.name = "李四";
                        human.sex = "女";
                    }
                    count = ++count % 2;

                    human.isWrited = true;
                    condition.signal();
                }


                //生产后释放锁
                human.lock.unlock();
            }

        }
    }

    public static class Consumer extends Thread {

        private Human human;
        private Condition condition;

        public Consumer(Human human, Condition condition) {
            this.human = human;
            this.condition = condition;
        }

        @Override
        public void run() {

            while (true) {

                human.lock.lock();

                if (human.isWrited) {
                    System.out.println(human.name + "--" + human.sex);
                    human.isWrited = false;
                    condition.signal();
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                human.lock.unlock();
            }

        }
    }

}
