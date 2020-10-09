package com.huihui.aligo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * 编程式事务工具类
 * @author minghui.y BG358486
 * @create 2020-10-06 20:40
 **/
@Component
//多例（原型）模式，避免多线程环境下线程A提交线程B的事务
@Scope("prototype")
public class TransactionHelper {

    private TransactionStatus transactionStatus;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 开启事务
     */
    public void begin() {
        //默认的传播属性
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
    }

    /**
     * 提交事务
     */
    public void commit() {
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}
