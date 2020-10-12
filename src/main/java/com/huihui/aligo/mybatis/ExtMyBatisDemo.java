package com.huihui.aligo.mybatis;

/**
 * @author minghui.y BG358486
 * @create 2020-10-11 13:25
 **/
public class ExtMyBatisDemo {

    public static void main(String[] args) {

        ExtStoreMapper extStoreMapper = ExtSqlSession.getMapper(ExtStoreMapper.class);
        extStoreMapper.insertStore("灰灰店铺", "code001", "浙江省杭州市");

    }
}
