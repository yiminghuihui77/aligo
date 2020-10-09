package com.huihui.aligo.model;

import com.huihui.aligo.annotation.ExtAutowire;
import com.huihui.aligo.annotation.ExtComponent;
import lombok.Data;
import lombok.ToString;

/**
 * @author minghui.y BG358486
 * @create 2020-10-07 22:46
 **/
@Data
@ToString
@ExtComponent
public class User {

    private String name;

    private Integer age;

    private String sex;

    @ExtAutowire
    private Foot foot;

    public void talk() {
        System.out.println("I am " + toString());
    }

    public void walk() {
        foot.walk();
    }
}
