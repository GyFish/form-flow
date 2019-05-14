package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.form.FormInfo;

/**
 * 表单信息 mapper
 *
 * @author geyu
 */
public interface FormInfoMapper {

    Integer insert(FormInfo formInfo);

    FormInfo findById(Integer id);

}
