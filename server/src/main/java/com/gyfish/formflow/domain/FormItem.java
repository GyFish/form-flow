package com.gyfish.formflow.domain;

import lombok.Data;

@Data
public class FormItem {

    private Integer id;

    private Integer formId;

    // 类型
    private String itemType;

    // 字段 key
    private String prop;

    // 字段名称
    private String label;

}
