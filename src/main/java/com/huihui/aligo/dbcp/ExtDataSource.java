package com.huihui.aligo.dbcp;

import java.sql.Connection;

/**
 * 自定义数据库连接池，提供获取连接和释放连接的抽象方法
 *
 * @author minghui.y
 * @create 2020-10-10 16:19
 **/
public interface ExtDataSource {

    /**
     * 获取一个数据库连接
     * @return
     */
    Connection getConnection();

    /**
     * 释放一个数据库连接
     * @param connection
     */
    void releaseConnection(Connection connection);

}
