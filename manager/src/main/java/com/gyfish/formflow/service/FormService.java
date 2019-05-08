package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.DFormItemMapper;
import com.gyfish.formflow.dao.DFormMapper;
import com.gyfish.formflow.domain.form.definition.DForm;
import com.gyfish.formflow.domain.form.definition.DFormItem;
import com.gyfish.formflow.vo.FormEditorVo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

@Service
public class FormService {

    @Resource
    private DFormMapper formMapper;

    @Resource
    private DFormItemMapper itemMapper;

    /**
     * 保存表单配置
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveDefinition(FormEditorVo formEditorVo) {

        // 先保存 form，填充主键
        DForm dForm = formEditorVo.getdForm();
        dForm.setCreateTime(new Date());
        this.saveFormDefinition(dForm);

        // 再保存 formItems
        List<DFormItem> dFormItems = formEditorVo.getdFormItems();
        dFormItems.forEach(i -> {
            i.setDFormId(dForm.getId());
            i.setCreateTime(new Date());
        });
        this.saveFormItemDefinition(dFormItems);

    }

    private void saveFormDefinition(DForm dForm) {

        formMapper.insert(dForm);
    }

    private void saveFormItemDefinition(List<DFormItem> dFormItems) {

        itemMapper.insertMany(dFormItems);
    }

    public Object getFormList() {

        return formMapper.getFormList();
    }

    public Object getFormItems(Integer dFormId) {

        return itemMapper.getFormItems(dFormId);
    }
}
