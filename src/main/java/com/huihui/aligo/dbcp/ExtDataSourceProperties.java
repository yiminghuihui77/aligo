package com.huihui.aligo.dbcp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义数据库配置bean
 *
 * @author minghui.y
 * @create 2020-10-10 16:25
 **/
@Component
@ConfigurationProperties(prefix = "ext.datasource")
@Data
public class ExtDataSourceProperties {

    /**
     * 数据库驱动类全限定名
     */
    private String driver;
    /**
     * 数据库账号
     */
    private String usernme;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 数据库连接url
     */
    private String url;
    /**
     * 空闲连接池最少连接数
     */
    private int minFreeConnections = 1;
    /**
     * 空闲连接池最大连接数
     */
    private int maxFreeConnections = 10;
    /**
     * 空闲连接池初始连接数
     */
    private int initConnections = 5;
    /**
     * 最大连接数
     */
    private int maxActicveConnections = 100;
    /**
     * 获取连接超时等待时间
     */
    private long connectionTimeOut = 30 * 1000;

}
