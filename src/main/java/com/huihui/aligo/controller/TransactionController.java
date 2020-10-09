package com.huihui.aligo.controller;

import com.huihui.aligo.model.Store;
import com.huihui.aligo.service.TransactionService;
import com.huihui.aligo.so.StoreSo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 事务控制器
 *
 * @author minghui.y BG358486
 * @create 2020-10-06 20:54
 **/
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    private static final String BATCH_ADD_STORE = "/addBatchStores";
    private static final String INSERT_STORE = "/insertStore";

    @RequestMapping(value = BATCH_ADD_STORE, method = RequestMethod.POST)
    public void addBatchStores() {
        transactionService.addBatchStore(buildStores());
    }

    @RequestMapping(value = INSERT_STORE, method = RequestMethod.POST)
    public void insertStore() {
         transactionService.insertStore(buildStores());
    }


    private List<Store> buildStores() {
        List<Store> stores =  new ArrayList<>();

        Store store1 = new Store("1789876765", "code009", "批量店铺1", "批量地址1");
        Store store2 = new Store("1789876760", "code010", "批量店铺2", "批量地址2");

        store1.setId(9);
        store2.setId(10);

        stores.add(store1);
        stores.add(store2);
        return stores;

    }

}
