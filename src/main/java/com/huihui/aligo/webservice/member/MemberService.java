package com.huihui.aligo.webservice.member;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * 会员服务（服务提供者）
 *
 * @author minghui.y
 * @create 2020-11-16 10:04 下午
 **/
@WebService
public class MemberService {

    @WebMethod
    public String getMemberInfo(Long id) {
        return "我是张三，id是：" + id;
    }


    public static void main(String[] args) {
        //发布服务
        Endpoint.publish("http://127.0.0.1:8090/service/MemberService", new MemberService());

        //访问http://127.0.0.1:8090/service/MemberService?wsdl可查看xml形式的服务信息

    }
}
