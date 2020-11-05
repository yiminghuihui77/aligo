package com.huihui.aligo.zookeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * 服务提供方
 *  作为一个进程注册到Zookeeper上
 * @author minghui.y BG358486
 * @create 2020-10-31 16:21
 **/
public class ZkServerProvider {

    private static final String CONNECTION_ADDRESS = "127.0.0.1:2181";

    private static final int PORT = 18080;


    public static void main(String[] args) throws InterruptedException {

        ZkClient client = new ZkClient(CONNECTION_ADDRESS, 5000, 5000);

        String path = "/test/server" + PORT;
        if (client.exists(path)) {
            client.delete(path);
        }
        //创建临时节点
        client.createEphemeral(path, "127.0.0.1:" + PORT);

        Thread.sleep(1000* 60);

    }

}
