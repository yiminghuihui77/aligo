package com.huihui.aligo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 *
 * @author minghui.y
 * @create 2020-11-27 5:30 下午
 **/
public class Producer {

    private static final String QUEUE_NAME = "ALIGO_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqHelper.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false,false, false, null);

        for (int i = 0; i< 50; i++) {
            String message = "灰灰生产消息：" + i;
            System.out.println("发布消息：" + message);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());


        }

        //释放资源
        channel.close();
        connection.close();

    }

}
