package com.yczuoxin.myservice.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.RelationOneToOne;
import com.mybatisflex.annotation.Table;
import lombok.Data;

@Table("INFO")
@Data
public class Info {

    @Id
    private Long id;

    private String value;

    private String description;

    @RelationOneToOne(selfField = "id", targetField = "infoId")
    private InfoDetail infoDetail;

}
