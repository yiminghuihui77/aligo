package com.huihui.aligo.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @author minghui.y
 * @create 2020-11-27 5:38 下午
 **/
public class Consumer {

    private static final String QUEUE_NAME = "ALIGO_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitMqHelper.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //RabbitMQ不要在同一时间给一个消费者超过一条消息。
        //换句话说，只有在消费者空闲的时候会发送下一条信息
        channel.basicQos(1);

        //定义消费逻辑
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "utf-8");
                System.out.println("消费者2：" + message);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //手动ack
                channel.basicAck(envelope.getDeliveryTag(), false);

            }
        };

        //必须自动应答（true），否则mq服务器会等待ack而不销毁消息
        channel.basicConsume(QUEUE_NAME,false,  consumer);

    }

}
