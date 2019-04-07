package com.gyfish.formflow.vo;


import com.gyfish.formflow.domain.FormDefinition;
import com.gyfish.formflow.domain.FormItemDefinition;

import java.util.List;

import lombok.Data;

@Data
public class FormEditorVo {

    private FormDefinition formDefinition;

    private List<FormItemDefinition> formItemDefinitions;


}
