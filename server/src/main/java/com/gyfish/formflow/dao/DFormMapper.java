package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.DFormExample;
import com.gyfish.formflow.domain.form.definition.DForm;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表单定义 mapper
 *
 * @author geyu
 */
public interface DFormMapper {

    int countByExample(DFormExample example);

    int deleteByExample(DFormExample example);

    int deleteByPrimaryKey(Integer id);

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

    int insertSelective(DForm record);

    List<DForm> selectByExample(DFormExample example);

    DForm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DForm record, @Param("example") DFormExample example);

    int updateByExample(@Param("record") DForm record, @Param("example") DFormExample example);

    int updateByPrimaryKeySelective(DForm record);

    int updateByPrimaryKey(DForm record);

}