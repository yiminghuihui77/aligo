package com.huihui.aligo.alibaba.nacos.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 订单服务（服务消费端）
 *
 * @author minghui.y
 * @create 2020-11-17 11:18 上午
 **/
@RestController
@RequestMapping("/nacos")
public class NacosOrderService {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getMemberDataFromNacos")
    public String getMemberDataFromNacos() {
        //根据服务名称，从注册中心nacos获取集群地址列表
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("aligo");
        //本地负载均衡获取一个地址，实现RPC远程调用
        String result = restTemplate.getForObject(serviceInstances.get(0).getUri() + "/getMemberData?id=111", String.class);
        return result;

    }
}
