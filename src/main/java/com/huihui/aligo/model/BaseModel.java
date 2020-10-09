package com.huihui.aligo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * model抽象父类
 *
 * @author minghui.y @BG358486
 * @create 2020-09-03 20:17
 **/
@Data
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = -6632391328936486655L;

    private Integer id;
    private Integer creatorId;
    private Integer updaterId;
    private String creatorName;
    private String updaterName;
    private Date createdTime;
    private Date updatedTime;
}
