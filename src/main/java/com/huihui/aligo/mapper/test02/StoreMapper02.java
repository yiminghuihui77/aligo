package com.huihui.aligo.mapper.test02;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author minghui.y BG358486
 * @create 2020-10-17 19:49
 **/
public interface StoreMapper02 {

    @Insert("insert into aligo_store(id, code, name, user_name, address) values(#{id}, #{code}, #{name}, #{userName}, #{address})")
    int insert(@Param("id") int id, @Param("code") String code, @Param("name") String name, @Param("userName") String userName, @Param("address") String address);
}
