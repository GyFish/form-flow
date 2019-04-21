package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.form.definition.DForm;

import java.util.List;

/**
 * 表单定义 mapper
 *
 * @author geyu
 */
public interface DFormMapper {

    /**
     * 插入一条表单定义
     *
     * @param dForm 表单定义
     * @return 插入数量
     */
    int insert(DForm dForm);

    /**
     * 查询表单定义列表
     *
     * @return 表单定义数组
     */
    List<DForm> getFormList();

}