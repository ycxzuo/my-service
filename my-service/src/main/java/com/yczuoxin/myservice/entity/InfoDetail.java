package com.yczuoxin.myservice.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;

@Data
@Table("INFO_DETAIL")
public class InfoDetail {

    @Id
    private Long id;

    private Long infoId;

    private String extendInfo;
}
