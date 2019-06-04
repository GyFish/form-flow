package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.flow.FlowInfo;

import java.util.List;

/**
 * @author geyu
 */
public interface FlowInfoMapper {

    void insertFlowInfo(FlowInfo flowInfo);

    List<FlowInfo> findAll();
}
