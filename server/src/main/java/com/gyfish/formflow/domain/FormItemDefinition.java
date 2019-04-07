package com.gyfish.formflow.domain;

import java.util.Date;

import lombok.Data;

@Data
public class FormItemDefinition {

    private Integer id;

    private Integer formId;

    // 字段 key
    private String prop;

    // 类型
    private String itemType;

    // 字段名称
    private String label;

    // 占位值
    private String placeholder;

    // 图标
    private String icon;

    // 输入框类型
    private String inputType;

    private Date createdAt;

    private Date updatedAt;


}
