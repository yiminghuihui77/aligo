package com.huihui.aligo.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.impl.AMQImpl;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author minghui.y
 * @create 2020-11-27 4:58 下午
 **/
public class RabbitMqHelper {

    public static Connection getConnection() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("aligo");
        factory.setPassword("huihui");
        factory.setPort(5672);
        factory.setVirtualHost("/aligo_huihui");
        return factory.newConnection();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
    }



}
