package com.huihui.aligo.thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 线程间数据通信
 *
 * @author minghui.y BG358486
 * @create 2020-09-10 23:25
 **/
public class DataBetweemThreadDemo {

    public static void main(String[] args) {

        try {

            Sender sender = new Sender();
            Receive receive = new Receive();
            PipedInputStream in = receive.get();
            PipedOutputStream out = sender.get();

            //管道连接
            out.connect(in);

            new Thread(sender).start();
            new Thread(receive).start();


        } catch (Exception e) {

        }

    }


    /**
     * 发送方
     */
    static class Sender implements Runnable {

        PipedOutputStream out = new PipedOutputStream();

        public PipedOutputStream get() {
            return out;
        }

        @Override
        public void run() {
            String message = "hello, I am " + Thread.currentThread().getName();

            try {
                //发送方向管道写入数据
                out.write(message.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Receive implements Runnable {

        private PipedInputStream in = new PipedInputStream();

        public PipedInputStream get() {
            return in;
        }

        //接收者从管道里读取数据
        @Override
        public void run() {
            byte[] buff = new byte[1024];

            try {
                in.read(buff);
                System.out.println(Thread.currentThread().getName() + "接收到信息：" + new String(buff));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
