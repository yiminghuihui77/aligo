package com.huihui.aligo.service;

import com.huihui.aligo.model.Store;

import java.util.List;

/**
 * @author minghui.y BG358486
 * @create 2020-10-06 21:00
 **/
public interface TransactionService {

    /**
     * 批量添加店铺
     * @param stores
     */
    void addBatchStore(List<Store> stores);

    /**
     * 插入店铺
     * @param stores
     * @return
     */
    void insertStore(List<Store> stores);

}
