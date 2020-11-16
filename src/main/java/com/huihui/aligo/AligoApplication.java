package com.huihui.aligo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * aligo
 * @author minghui.y
 */
@EnableDubbo
@SpringBootApplication
@EnableConfigurationProperties
public class AligoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AligoApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(AligoApplication.class, args);
        LOGGER.info("AliGo项目于2020-09-03正式启动，冲阿里！！！");
    }

}
