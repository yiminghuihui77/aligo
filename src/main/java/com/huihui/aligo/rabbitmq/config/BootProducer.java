package com.huihui.aligo.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author minghui.y
 * @create 2020-11-28 3:54 下午
 **/
@Component
public class BootProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String message) {
        Message msg = new Message(message.getBytes(), null);
        amqpTemplate.send(msg);
    }

}
