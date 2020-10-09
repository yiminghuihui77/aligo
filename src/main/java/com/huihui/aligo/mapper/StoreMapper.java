package com.huihui.aligo.mapper;

import com.huihui.aligo.so.StoreSo;
import com.huihui.aligo.model.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author minghui.y @BG358486
 * @create 2020-09-03 20:37
 **/
@Mapper
public interface StoreMapper {

    /**
     * 条件查询店铺
     * @param so
     * @return
     */
    Store getStoreBySo(StoreSo so);

    /**
     * 查询所有店铺
     * @return
     */
    List<Store> searchStores();
}
