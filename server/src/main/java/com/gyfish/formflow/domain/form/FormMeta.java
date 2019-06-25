package com.gyfish.formflow.domain.form;

import com.alibaba.fastjson.JSONObject;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author geyu1
 */
@Data
@Document("form")
public class FormMeta {

    @Id
    private String id;

    private String appId;

    private String uuid;

    private String title;

    private String value;

    private Date createTime;

    private Date updateTime;

    private List<JSONObject> items;

}
