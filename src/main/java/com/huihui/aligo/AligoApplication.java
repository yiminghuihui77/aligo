package com.huihui.aligo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * aligo
 * @author minghui.y
 */
@EnableFeignClients
@EnableDubbo
@SpringBootApplication
@EnableConfigurationProperties
public class AligoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AligoApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(AligoApplication.class, args);
        LOGGER.info("AliGo项目于2020-09-03正式启动，冲阿里！！！");
    }

    @Bean
    //本地负载均衡
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
