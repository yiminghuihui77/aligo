package com.huihui.aligo.controller;

import com.huihui.aligo.so.StoreSo;
import com.huihui.aligo.model.Store;
import com.huihui.aligo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 第一个控制器
 *
 * @author minghui.y @BG358486
 * @create 2020-09-03 20:05
 **/
@RestController
@RequestMapping("/store")
class StoreController {

    private static final String GET_STORE_BY_SO = "/getStoreBySo";
    private static final String SEARCH_STORES = "/searchStores";

    @Autowired
    private StoreService storeService;


    @RequestMapping(value = GET_STORE_BY_SO, method = RequestMethod.POST)
    @Transactional
    public Store getStoreBySo(StoreSo so) {
        return storeService.getStoreBySo(so);
    }


    @RequestMapping(value = SEARCH_STORES, method = RequestMethod.GET)
    public List<Store> searchStores() {
        return storeService.searchStores();
    }






}
