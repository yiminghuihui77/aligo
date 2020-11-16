package com.huihui.aligo.dubbo;

/**
 * 订单服务接口（服务提供者）
 *  一般api接口作为一个单独的module，供外部引用
 * @author minghui.y
 * @create 2020-11-15 11:50 上午
 **/
public interface MemberService {

    /**
     * 获取会员信息
     */
    String getMember(Long id);
}
