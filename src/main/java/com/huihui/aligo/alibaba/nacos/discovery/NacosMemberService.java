package com.huihui.aligo.alibaba.nacos.discovery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghui.y
 * @create 2020-11-17 10:50 上午
 **/
@RestController
public class NacosMemberService {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/getMemberData")
    public String getMemberData(Long id) {
        return "服务提供者:" + port + " ：member:" + id;
    }
}
