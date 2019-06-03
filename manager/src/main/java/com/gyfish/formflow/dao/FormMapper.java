package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.form.FormInfo;

import java.util.List;

/**
 * 表单信息 mapper
 *
 * @author geyu
 */
public interface FormMapper {

    Integer insert(FormInfo formInfo);

    FormInfo findById(Integer id);

    List<FormInfo> findAll();
}
