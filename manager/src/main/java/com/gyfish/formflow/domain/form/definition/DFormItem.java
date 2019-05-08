package com.gyfish.formflow.domain.form.definition;

import java.util.Date;

import lombok.Data;

/**
 * 表单元素定义
 *
 * @author geyu
 */
@Data
public class DFormItem {

    private Integer id;

    private Integer dFormId;

    private String prop;

    private String itemType;

    private String label;

    private String placeholder;

    private String icon;

    private String inputType;

    private Date createTime;

    private Date updateTime;

}