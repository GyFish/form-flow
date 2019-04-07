package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.DFormExample;
import com.gyfish.formflow.domain.FormDefinition;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DFormMapper {
    int countByExample(DFormExample example);

    int deleteByExample(DFormExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FormDefinition record);

    int insertSelective(FormDefinition record);

    List<FormDefinition> selectByExample(DFormExample example);

    FormDefinition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FormDefinition record, @Param("example") DFormExample example);

    int updateByExample(@Param("record") FormDefinition record, @Param("example") DFormExample example);

    int updateByPrimaryKeySelective(FormDefinition record);

    int updateByPrimaryKey(FormDefinition record);
}