package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.DFormOption;
import com.gyfish.formflow.domain.DFormOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DFormOptionMapper {
    int countByExample(DFormOptionExample example);

    int deleteByExample(DFormOptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DFormOption record);

    int insertSelective(DFormOption record);

    List<DFormOption> selectByExample(DFormOptionExample example);

    DFormOption selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DFormOption record, @Param("example") DFormOptionExample example);

    int updateByExample(@Param("record") DFormOption record, @Param("example") DFormOptionExample example);

    int updateByPrimaryKeySelective(DFormOption record);

    int updateByPrimaryKey(DFormOption record);
}