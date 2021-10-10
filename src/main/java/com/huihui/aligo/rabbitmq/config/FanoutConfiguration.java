package com.huihui.aligo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * springboot整合rabbitmq-发布订阅模式
 *
 * @author minghui.y
 * @create 2020-11-28 3:42 下午
 **/
@Configuration
public class FanoutConfiguration {

    private static final String SMS_QUEUE = "SMS_QUEUE";
    private static final String EMAIL_QUEUE = "EMAIL_QUEUE";
    private static final String EXCHANGE_NAME = "FANOUT_EXCHANGE";

    @Bean
    public Queue smsQueue() {
        return new Queue(SMS_QUEUE);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    /**
     * sms队列绑定exchange
     * @param smsQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    public Binding bindingSmsExchange(Queue smsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(smsQueue).to(fanoutExchange);
    }

    /**
     * email队列绑定exchange
     * @param emailQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    public Binding bindingEmailExchange(Queue emailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(emailQueue).to(fanoutExchange);
    }






}
