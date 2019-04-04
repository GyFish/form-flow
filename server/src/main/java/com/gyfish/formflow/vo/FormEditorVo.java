package com.gyfish.formflow.vo;

import com.gyfish.formflow.domain.Form;
import com.gyfish.formflow.domain.FormItem;

import java.util.List;

import lombok.Data;

@Data
public class FormEditorVo {

    private Form form;

    private List<FormItem> formItems;


}
