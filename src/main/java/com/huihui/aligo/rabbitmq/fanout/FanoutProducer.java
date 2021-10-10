package com.huihui.aligo.rabbitmq.fanout;

import com.huihui.aligo.rabbitmq.RabbitMqHelper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发布订阅模式-fanout
 * fanout模式：交换机会向所有绑定到交换机的队列投递相同的消息
 *
 * @author minghui.y
 * @create 2020-11-28 1:55 下午
 **/
public class FanoutProducer {

    public static final String FANOUT_EXCHANGE = "fanout_exchange_name";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitMqHelper.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(FANOUT_EXCHANGE, "fanout");

        //开始事务
        channel.txSelect();

        try {
            String message = "我是fanout的消息";

            channel.basicPublish(FANOUT_EXCHANGE, "", null, message.getBytes());
            System.out.println("fanout生产者投递消息：" + message);

            int i = 1/0;

        } catch (Exception e) {
            e.printStackTrace();
            channel.txRollback();
        } finally {
            channel.txCommit();

            //释放资源
            channel.close();
            connection.close();
        }

    }


}
