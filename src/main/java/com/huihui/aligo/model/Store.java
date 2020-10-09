package com.huihui.aligo.model;

import com.huihui.aligo.annotation.ExtComponent;
import lombok.Data;

/**
 * 店铺model
 *
 * @author minghui.y @BG358486
 * @create 2020-09-03 20:17
 **/
@Data
@ExtComponent
public class Store extends BaseModel {
    private String userName;
    private String code;
    private String name;
    private String address;

    public Store() {

    }

    public Store(String userName, String code, String name, String address) {
        this.userName = userName;
        this.code = code;
        this.name = name;
        this.address = address;
    }

    public void sale() {
        System.out.println("店铺: " + toString() + ", 正在售卖...");
    }

    public Store(String userName, String name, String address) {
        this.userName = userName;
        this.name = name;
        this.address = address;
    }



    @Override
    public String toString() {
        return "Store{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
