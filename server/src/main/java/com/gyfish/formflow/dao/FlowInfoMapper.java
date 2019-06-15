package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.flow.FlowMeta;

import java.util.List;

/**
 * @author geyu
 */
public interface FlowInfoMapper {

    void insertFlowInfo(FlowMeta flowMeta);

    List<FlowMeta> findAll();
}
