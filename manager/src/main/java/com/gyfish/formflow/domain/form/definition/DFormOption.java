package com.gyfish.formflow.domain.form.definition;

import lombok.Data;

/**
 * 表单元素的选项定义
 *
 * @author geyu
 */
@Data
public class DFormOption {

    private Integer id;

    private String itemId;

    private String label;

    private String value;

}