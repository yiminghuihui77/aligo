package com.huihui.aligo.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author minghui.y BG358486
 * @create 2020-10-31 14:29
 **/
public class ZookeeperDemo01 {

    private static final String CONNECTION_ADDRESS = "127.0.0.1:2181";
    private static final int SESSION_TIME_OUT = 2000;


    public static void main(String[] args) throws IOException, InterruptedException {

        final  CountDownLatch countDownLatch = new CountDownLatch(1);


        //连接zookeeper
        ZooKeeper zooKeeper  = new ZooKeeper(CONNECTION_ADDRESS, SESSION_TIME_OUT, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                Event.KeeperState keeperState = event.getState();

                //获取事件类型
                Event.EventType eventType = event.getType();

                if (Event.KeeperState.SyncConnected == keeperState) {
                    if (Event.EventType.None == eventType) {
                        countDownLatch.countDown();
                        System.out.println("zookeeper connect success...");
                    }
                }

            }
        });


        //创建一个节点

        try {
            //等待zookeeper连接成功后再尝试创建节点
            countDownLatch.await();

            String result = zooKeeper.create("/com", "comData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            System.out.println("创建节点成功：" + result);

        } catch (Exception e) {
            System.out.println("something go wrong...");
            e.printStackTrace();
        } finally {
           zooKeeper.close();
        }



    }

}
