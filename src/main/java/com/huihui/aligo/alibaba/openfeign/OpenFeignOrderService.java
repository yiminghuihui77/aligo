package com.huihui.aligo.alibaba.openfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OpenFeign方式调用（消费端）
 *
 * @author minghui.y
 * @create 2020-11-17 3:49 下午
 **/
@RestController
public class OpenFeignOrderService {
    @Autowired
    private NacosMemberServiceFeign memberServiceFeign;

    @RequestMapping("/getMemberDataByOpenFeign")
    public String getMemberDataByOpenFeign(Long id) {
        String result = memberServiceFeign.getMemberDataByOpenFeign(id);
        return "open feign方式调用结果：" + result;
    }
}
