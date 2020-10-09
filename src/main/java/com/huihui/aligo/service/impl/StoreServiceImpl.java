package com.huihui.aligo.service.impl;

import com.huihui.aligo.so.StoreSo;
import com.huihui.aligo.model.Store;
import com.huihui.aligo.repository.StoreRepository;
import com.huihui.aligo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author minghui.y @BG358486
 * @create 2020-09-03 20:33
 **/
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;


    @Override
    public Store getStoreBySo(StoreSo so) {
        return storeRepository.getStoreBySo(so);
    }

    @Override
    public List<Store> searchStores() {
        try {
            int i = 1/0;
            return storeRepository.searchStores();
        } catch (Exception e) {
            System.out.println("我是抛出的异常");
        }

        return null;
    }
}
