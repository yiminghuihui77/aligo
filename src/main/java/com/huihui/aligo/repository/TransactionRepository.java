package com.huihui.aligo.repository;

import com.huihui.aligo.model.Store;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author minghui.y BG358486
 * @create 2020-10-06 20:56
 **/
public interface TransactionRepository {


    /**
     * 插入店铺
     * @param store
     * @param connection
     * @return
     * @throws SQLException
     */
    int addStore(Store store, Connection connection) throws SQLException;

    /**
     * 插入店铺
     * @param stores
     * @return
     */
    void insertStore(List<Store> stores);

}
