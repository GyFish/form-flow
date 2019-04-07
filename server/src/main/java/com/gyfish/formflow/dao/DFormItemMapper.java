package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.DFormItem;
import com.gyfish.formflow.domain.DFormItemExample;
import com.gyfish.formflow.domain.FormItemDefinition;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DFormItemMapper {

    Integer insertMany(List<FormItemDefinition> formItemDefinitions);

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