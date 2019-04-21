package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.form.definition.DFormItem;

import java.util.List;

/**
 * 表单元素 mapper
 *
 * @author geyu
 */
public interface DFormItemMapper {

    /**
     * 批量插入表单项
     *
     * @param dFormItems 表单元素
     * @return 插入个数
     */
    Integer insertMany(List<DFormItem> dFormItems);

    /**
     * 根据表单 id 获取 元素列表
     *
     * @param dFormId 表单定义表的 id
     * @return 表单元素列表
     */
    List<DFormItem> getFormItems(Integer dFormId);

}