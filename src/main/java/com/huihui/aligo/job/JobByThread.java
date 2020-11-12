package com.huihui.aligo.job;

public class JobByThread {

    public static void main(String[] args) {

        new Thread(new Task()).run();


    }


    public static class Task implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("每5秒执行一次任务");
            }
        }
    }
}
