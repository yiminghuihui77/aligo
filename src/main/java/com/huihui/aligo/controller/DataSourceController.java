package com.huihui.aligo.controller;

import com.huihui.aligo.dbcp.ExtDataSourcePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;

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
        Task task = new Task(extDataSourcePool);
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();


    }

    public static class Task implements Runnable {

        private ExtDataSourcePool extDataSourcePool;

        public Task(ExtDataSourcePool extDataSourcePool) {
            this.extDataSourcePool = extDataSourcePool;
        }

        @Override
        public void run() {
            for (int i = 0;i < 10; i++) {
                Connection connection = extDataSourcePool.getConnection();
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                extDataSourcePool.releaseConnection(connection);
            }
        }
    }
}
