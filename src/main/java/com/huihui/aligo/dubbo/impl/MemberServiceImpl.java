package com.huihui.aligo.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.huihui.aligo.dubbo.MemberService;

/**
 * 会员服务具体实现类
 * 注意：@Service注解使用的是Dubbo提供的，而不是Spring提供的
 * @author minghui.y
 * @create 2020-11-15 11:52 上午
 **/
@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public String getMember(Long id) {
        return "我是服务提供者：会员服务：" + id;
    }
}
