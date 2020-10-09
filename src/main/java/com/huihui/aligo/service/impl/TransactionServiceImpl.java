package com.huihui.aligo.service.impl;

import com.huihui.aligo.annotation.MyTransactional;
import com.huihui.aligo.jdbc.JdbcUtils;
import com.huihui.aligo.model.Store;
import com.huihui.aligo.repository.TransactionRepository;
import com.huihui.aligo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 编程式事务（手写）
 * @author minghui.y BG358486
 * @create 2020-10-06 21:01
 **/
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    /**
     * 原生JDBC方式操作数据库
     * @param stores
     */
    @Override
    public void addBatchStore(List<Store> stores) {

        Connection connection = JdbcUtils.getConnection();

        try {

            //设置手动提交事务
            connection.setAutoCommit(false);

            transactionRepository.addStore(stores.get(0), connection);

            //抛出异常
            int i = 1 / 0;

            transactionRepository.addStore(stores.get(1), connection);

            //提交事务
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * 使用Spring-jdbc的JdbcTemplate操作数据库
     * @param stores
     * @return
     */
    @Override
    @MyTransactional
    @Transactional
    public void insertStore(List<Store> stores) {
         transactionRepository.insertStore(stores);
    }
}
