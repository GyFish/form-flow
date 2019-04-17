package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.form.definition.DFormItem;
import com.gyfish.formflow.domain.DFormItemExample;
import com.gyfish.formflow.domain.FormItemDefinition;

import java.util.List;
import org.apache.ibatis.annotations.Param;

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

    int countByExample(DFormItemExample example);

    int deleteByExample(DFormItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FormItemDefinition formItemDefinition);

    int insertSelective(FormItemDefinition record);

    List<FormItemDefinition> selectByExample(DFormItemExample example);

    DFormItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FormItemDefinition record, @Param("example") DFormItemExample example);

    int updateByExample(@Param("record") FormItemDefinition record, @Param("example") DFormItemExample example);

    int updateByPrimaryKeySelective(FormItemDefinition record);

    int updateByPrimaryKey(FormItemDefinition record);
}