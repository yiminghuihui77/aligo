package com.huihui.aligo.alibaba.nacos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos实现分布式配置中心测试
 *
 * @author minghui.y
 * @create 2020-11-17 4:53 下午
 **/
@RefreshScope
@RestController
public class NacosConfigController {

    @Value("${huihui.config:默认值}")
    private String configData;

    @RequestMapping("/getConfigByNacos")
    public String getConfigByNacos() {
        return "Nacos分布式配置中心获取结果：" + configData;
    }
}
