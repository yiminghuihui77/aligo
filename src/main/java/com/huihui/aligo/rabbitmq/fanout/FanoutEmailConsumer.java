package com.huihui.aligo.rabbitmq.fanout;

import com.huihui.aligo.rabbitmq.RabbitMqHelper;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author minghui.y
 * @create 2020-11-28 2:04 下午
 **/
public class FanoutEmailConsumer {

    private static final String EMAIL_QUEUE = "email_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitMqHelper.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(EMAIL_QUEUE, false, false, false, null);

        //绑定交换机
        channel.queueBind(EMAIL_QUEUE, FanoutProducer.FANOUT_EXCHANGE, "");

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("EMAIL消费者：" + new String(body));
            }
        };
        channel.basicConsume(EMAIL_QUEUE,true, consumer);

    }
}
