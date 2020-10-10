package com.huihui.aligo.dbcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义数据库连接池
 *
 * @author minghui.y
 * @create 2020-10-10 16:53
 **/
@Component
public class ExtDataSourcePool implements ExtDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtDataSourcePool.class);

    /**
     * 空闲连接池
     */
    private final Vector<Connection> freePool = new Vector<>();
    /**
     * 工作连接池
     */
    private final Vector<Connection> activePool = new Vector<>();
    /**
     * 存活的连接总数：空闲 + 工作
     */
    private final AtomicInteger totalConnections = new AtomicInteger(0);

    /**
     * 初始化空闲连接池
     */
    public ExtDataSourcePool() {
        try {
            Connection connection;
            for (int i = 0;i < dataSourceProperties.getInitConnections();i++) {
                connection = rawGetConnection();
                if (isAvailable(connection)) {
                    freePool.add(connection);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Autowired
    private ExtDataSourceProperties dataSourceProperties;


    /**
     * 从连接池中获取一个连接
     * @return
     */
    @Override
    public synchronized Connection getConnection() {
        Connection connection = null;

        try {
            if (totalConnections.get() < dataSourceProperties.getMaxActicveConnections()) {
                //当前存活连接数 < 最大连接数
                if (!freePool.isEmpty()) {
                    //有空闲连接，则从空闲池取连接
                    connection = freePool.remove(0);
                    LOGGER.info("从空闲池中获取一个连接：{}", connection);
                } else {
                    //没有空闲连接，则新建连接
                    connection = rawGetConnection();
                    LOGGER.info("新建一个连接：{}", connection);
                }
                //记录到工作连接池中
                activePool.add(connection);

            } else {
                //当前存活连接数 > 最大连接数
                //执行等待
                LOGGER.info("连接数已超标，执行等待");
                wait(dataSourceProperties.getConnectionTimeOut());
                //重试获取连接
                connection = getConnection();
            }

            //判断连接是否有效
            if (!isAvailable(connection)) {
                //无效，重试获取连接
                connection = getConnection();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 释放一个连接
     * @param connection
     */
    @Override
    public synchronized void releaseConnection(Connection connection) {

        try {
            if (!isAvailable(connection)) {
                return;
            }
            if (freePool.size() >= dataSourceProperties.getMaxFreeConnections()) {
                //当前空闲连接数 >= 空闲池最大连接数
                //直接释放
                connection.close();
                totalConnections.decrementAndGet();
                LOGGER.info("多余的连接，直接销毁");
            } else {
                //放入空闲连接池
                freePool.add(connection);
                LOGGER.info("被释放的连接，进入空闲池");
            }

            //从工作连接池中移除
            activePool.remove(connection);
            //唤醒
            notifyAll();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 判断连接是否有效
     * @param connection
     * @return
     * @throws SQLException
     */
    private boolean isAvailable(Connection connection) throws SQLException {
        if (connection == null || connection.isClosed()) {
            return false;
        }
        return true;
    }


    /**
     * 原生获取数据库连接
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection rawGetConnection() {
        Connection connection = null;
        try {
            Class.forName(dataSourceProperties.getDriver());

            connection = DriverManager.getConnection(dataSourceProperties.getUrl(),
                    dataSourceProperties.getUsernme(),
                    dataSourceProperties.getPassword());
            //每新建连接，计数值加一
            totalConnections.incrementAndGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
