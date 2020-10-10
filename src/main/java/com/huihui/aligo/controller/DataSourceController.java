package com.huihui.aligo.controller;

import com.huihui.aligo.dbcp.ExtDataSourcePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghui.y
 * @create 2020-10-10 18:22
 **/
@RestController
@RequestMapping("/datasource")
public class DataSourceController {

    @Autowired
    private ExtDataSourcePool extDataSourcePool;


    @RequestMapping(value = "/testPool", method = RequestMethod.POST)
    public void testDatasourcePool() {

    }
}
