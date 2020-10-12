package com.huihui.aligo.mybatis;

import com.huihui.aligo.annotation.ExtInsert;
import com.huihui.aligo.annotation.ExtParam;
import com.huihui.aligo.model.Store;

import java.util.List;

/**
 * 注解版mybatis实现
 *
 * @author minghui.y
 * @create 2020-10-12 11:09
 **/
public interface ExtStoreMapper {

    /**
     * 查询
     * @return
     */
    List<Store> searchStores();

    /**
     * 插入一个店铺
     * @param name
     * @param code
     * @param address
     * @return
     */
    @ExtInsert("insert into aligo_store(code, name, address) values(#{code}, #{name}, #{address})")
    int insertStore(@ExtParam("name") String name, @ExtParam("code") String code, @ExtParam("address") String address);

}
