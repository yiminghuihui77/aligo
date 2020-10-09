package com.huihui.aligo.service;

import com.huihui.aligo.so.StoreSo;
import com.huihui.aligo.model.Store;

import java.util.List;

/**
 * @author minghui.y @BG358486
 * @create 2020-09-03 20:32
 **/
public interface StoreService {

    /**
     * 条件查询店铺
     * @param so
     * @return
     */
    Store getStoreBySo(StoreSo so);

    /**
     * 查询所有店铺
     * @return
     */
    List<Store> searchStores();
}
