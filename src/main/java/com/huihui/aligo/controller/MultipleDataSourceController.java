package com.huihui.aligo.controller;

import com.huihui.aligo.mapper.test01.StoreMapper01;
import com.huihui.aligo.mapper.test02.StoreMapper02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多数据源测试Controller
 *
 * @author minghui.y BG358486
 * @create 2020-10-17 19:55
 **/
@RestController
@RequestMapping("/multiple")
public class MultipleDataSourceController {

    @Autowired
    private StoreMapper01 storeMapper01;

    @Autowired
    private StoreMapper02 storeMapper02;

    @RequestMapping(value = "/insert01", method = RequestMethod.GET)
    public int insertStore01(@RequestParam("id") int id,
                                @RequestParam("code") String code,
                                @RequestParam("name") String name,
                                @RequestParam("userName") String userName,
                                @RequestParam("address") String address) {
        return storeMapper01.insert(id, code, name, userName, address);
    }

    @RequestMapping(value = "/insert02", method = RequestMethod.GET)
    public int insertStore02(@RequestParam("id") int id,
                             @RequestParam("code") String code,
                             @RequestParam("name") String name,
                             @RequestParam("userName") String userName,
                             @RequestParam("address") String address) {
        return storeMapper02.insert(id, code, name, userName, address);
    }

}
