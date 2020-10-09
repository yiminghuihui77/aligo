package com.huihui.aligo.repository.impl;

import com.huihui.aligo.jdbc.JdbcUtils;
import com.huihui.aligo.model.Store;
import com.huihui.aligo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author minghui.y BG358486
 * @create 2020-10-06 20:56
 **/
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 使用原始的JDBC数据库操作方式
     * @param store
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public int addStore(Store store, Connection connection) throws SQLException {

        String sql = "insert into aligo_store(id, code, user_name, name, address) values(?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, store.getId());
        ps.setString(2, store.getCode());
        ps.setString(3, store.getUserName());
        ps.setString(4, store.getName());
        ps.setString(5, store.getAddress());

        return ps.executeUpdate();
    }

    /**
     * JdbcTemplate：spring-jdbc提供的数据库核心操作类
     * @param stores
     * @return
     */
    @Override
    public void insertStore(List<Store> stores) {
        String sql = "insert into aligo_store(id, code, user_name, name, address) values(?, ?, ?, ?, ?)";

         jdbcTemplate.update(sql,
                stores.get(0).getId(),
                stores.get(0).getCode(),
                stores.get(0).getUserName(),
                stores.get(0).getName(),
                stores.get(0).getAddress());

         int i = 1 / 0;
        System.out.println("插入第二个对象");


        jdbcTemplate.update(sql,
                stores.get(1).getId(),
                stores.get(1).getCode(),
                stores.get(1).getUserName(),
                stores.get(1).getName(),
                stores.get(1).getAddress());


    }


}
