package com.huihui.aligo.repository.impl;

import com.huihui.aligo.so.StoreSo;
import com.huihui.aligo.mapper.StoreMapper;
import com.huihui.aligo.model.Store;
import com.huihui.aligo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author minghui.y @BG358486
 * @create 2020-09-03 20:34
 **/
@Repository
public class StoreRepositoryImpl implements StoreRepository {

    @Autowired
    private StoreMapper storeMapper;


    @Override
    public Store getStoreBySo(StoreSo so) {
        return storeMapper.getStoreBySo(so);
    }

    @Override
    public List<Store> searchStores() {
        return storeMapper.searchStores();
    }
}
