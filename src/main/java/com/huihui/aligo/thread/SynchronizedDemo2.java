package com.huihui.aligo.thread;

/**
 * 线程间通信
 *
 * Object.wait、notify、notifyAll
 *
 * @author minghui.y BG358486
 * @create 2020-09-10 20:14
 **/
public class SynchronizedDemo2 {

    private String name = "张三";
    private String sex = "男";
    /**
     * 是否已经被修改
     * true ；是
     * false：否
     */
    private boolean isSetted = false;

    /**
     * 修改时锁住对象
     * @param name
     * @param sex
     */
    public synchronized void set(String name, String sex)  {
        if (!isSetted) {
            //未被修改，则修改
            this.name = name;
            //若不加 synchronized，此处可能出现线程同步问题
            this.sex = sex;
            isSetted = true;
           //修改后，提醒别的线程可以读取了
           this.notify();
        }

        //已被修改，那就等着吧
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取时锁住对象
     */
    public synchronized void get() {
        if (isSetted) {
            //已经修改过，则读取
            System.out.println(name + "-----" + sex);

            isSetted = false;
            //读取后，提醒别的线程可以修改了
           this.notify();
        }

        //已被读取，那就等着
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生产者：不断修改共享数据
     */
    public static class Producer implements Runnable {

        private SynchronizedDemo2 person;

        public Producer(SynchronizedDemo2 person) {
            this.person = person;
        }

        @Override
        public void run() {
            int i = 0;

            while (true) {
                if (i == 0) {
                    //双数
                    person.set("李四", "女");
                } else {
                    //单数
                    person.set("张三", "男");
                }
                i = (i + 1) % 2;
            }
        }
    }

    /**
     * 消费者：不停读取共享数据
     */
    public static class Consumer implements Runnable {

        private SynchronizedDemo2 person;

        public Consumer(SynchronizedDemo2 person) {
            this.person = person;
        }

        @Override
        public void run() {
            while (true) {
                person.get();
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo2 person = new SynchronizedDemo2();

        Producer producer = new Producer(person);
        Consumer consumer = new Consumer(person);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

}
