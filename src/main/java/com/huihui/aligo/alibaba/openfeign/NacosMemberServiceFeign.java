package com.huihui.aligo.alibaba.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * OpenFiegn方式调用服务
 *  服务提供者一般暴露api接口（含@RequestMapping注解），OpenFeign接口一般继承该接口即可
 * @author minghui.y
 * @create 2020-11-17 3:45 下午
 **/
@FeignClient("aligo")
public interface NacosMemberServiceFeign {

    @RequestMapping("/getMemberData")
    String getMemberDataByOpenFeign(@RequestParam Long id);
}
