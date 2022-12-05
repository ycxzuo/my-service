package com.yczuoxin.myservice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("INFO")
@Data
public class Info {

    @TableId
    private Long id;

    private String value;

    private String description;

}
