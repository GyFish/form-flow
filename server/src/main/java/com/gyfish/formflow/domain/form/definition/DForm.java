package com.gyfish.formflow.domain.form.definition;

import com.gyfish.formflow.domain.FormItemDefinition;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 表单定义
 *
 * @author geyu
 */
@Data
public class DForm {

    private Integer id;

    private String title;

    private Date createdAt;

    private Date updatedAt;

    private List<FormItemDefinition> formItemDefinitions;

}
