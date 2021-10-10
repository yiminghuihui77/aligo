package com.huihui.aligo.thread;

/**
 * 引出线程同步问题：线程间共享的数据的安全性，资源数据的访问不同步！
 * @author minghui.y BG358486
 * @create 2020-09-09 20:50
 **/
public class SynchronizedDemo {

    public static void main(String[] args) {

        SaleTickets saleTicketsTask = new SaleTickets();

        System.out.println("4个售票窗同时售卖共20张票...");

        new Thread(saleTicketsTask).start();
        new Thread(saleTicketsTask).start();

//        try {
//            Thread.sleep(200);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        saleTicketsTask.flag = false;

        new Thread(saleTicketsTask).start();
        new Thread(saleTicketsTask).start();
    }



    public static class SaleTickets implements Runnable {
        //票数需要足够多，才能看出多线程效果
        private Integer tickets = 2000;
        private boolean flag = true;

        @Override
        public void run() {

          while (tickets > 0) {
              if (flag) {
                  this.sale1();
              } else {
                  this.sale2();
              }

          }

        }

        private void sale1() {
           synchronized (this) {
               if (tickets > 0) {

                   try {
                       //此处休眠，让其他线程执行
                       Thread.sleep(1);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

                   System.out.println("售票窗口" + Thread.currentThread().getName() + "正卖出票：" + tickets);
                   tickets--;
               }
           }
        }

        /**
         * 售票方法2
         */
        private synchronized void sale2() {
            //下面的代码可能出现线程同步问题
            if (tickets > 0) {

                try {
                    //此处休眠，让其他线程执行
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("售票窗口" + Thread.currentThread().getName() + "正卖出票：" + tickets);
                tickets--;
            }
        }
    }
}
