package com.gyfish.formflow.vo;

import com.gyfish.formflow.domain.form.definition.DForm;
import com.gyfish.formflow.domain.form.definition.DFormItem;

import java.util.List;

import lombok.Data;

/**
 * 用于表单编辑器保存接口的 vo 对象
 *
 * @author geyu
 */
public class FormEditorVo {

    private DForm dForm;

    private List<DFormItem> dFormItems;

    public DForm getdForm() {
        return dForm;
    }

    public void setdForm(DForm dForm) {
        this.dForm = dForm;
    }

    public List<DFormItem> getdFormItems() {
        return dFormItems;
    }

    public void setdFormItems(List<DFormItem> dFormItems) {
        this.dFormItems = dFormItems;
    }
}
