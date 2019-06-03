package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.flow.FlowNode;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author geyu
 */
public interface FlowNodeMapper {

    void insertFlowNodeBatch(@Param("nodes") List<FlowNode> nodes);

    List<FlowNode> getNodeList(String uuid);

}
