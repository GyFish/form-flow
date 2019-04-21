package com.gyfish.formflow.domain.form.definition;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 表单定义
 *
 * @author geyu
 */
@Data
public class DForm {

    private Integer id;

    private String title;

    private Date createTime;

    private Date updateTime;

    private List<DFormItem> dFormItems;

}
