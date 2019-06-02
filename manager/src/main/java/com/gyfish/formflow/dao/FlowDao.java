package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.flow.FlowInfo;
import com.gyfish.formflow.domain.flow.FlowNode;
import com.gyfish.formflow.domain.form.FormInfo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author geyu
 */
public interface FlowDao {

    void insertFlowInfo(FlowInfo flowInfo);

    void insertFlowNodeBatch(@Param("nodes") List<FlowNode> nodes);

    FlowInfo findById(Integer id);

    List<FlowInfo> findAll();

}
