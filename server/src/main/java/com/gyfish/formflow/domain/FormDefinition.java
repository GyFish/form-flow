package com.gyfish.formflow.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FormDefinition {

    private Integer id;

    private String title;

    private Date createdAt;

    private Date updatedAt;

    private List<FormItemDefinition> formItemDefinitions;

}
