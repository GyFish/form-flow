package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.DFormItemMapper;
import com.gyfish.formflow.dao.DFormMapper;
import com.gyfish.formflow.domain.FormDefinition;
import com.gyfish.formflow.domain.FormItemDefinition;
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

    // 保存表单配置
    @Transactional
    public void saveFormEditor(FormEditorVo formEditorVo) {


        // 先保存 form，填充主键
        FormDefinition formDefinition = formEditorVo.getFormDefinition();
        formDefinition.setCreatedAt(new Date());
        this.saveFormDefinition(formDefinition);

        // 再保存 formItems
        List<FormItemDefinition> itemDefinitions = formEditorVo.getFormItemDefinitions();
        itemDefinitions.forEach(i -> {
            i.setFormId(formDefinition.getId());
            i.setCreatedAt(new Date());
        });
        this.saveFormItemDefinition(itemDefinitions);

    }

    private void saveFormDefinition(FormDefinition formDefinition) {

        formMapper.insert(formDefinition);
    }

    public void saveFormItemDefinition(FormItemDefinition formItemDefinition) {

        itemMapper.insert(formItemDefinition);
    }

    private void saveFormItemDefinition(List<FormItemDefinition> formItemDefinitions) {

        itemMapper.insertMany(formItemDefinitions);
    }

}
