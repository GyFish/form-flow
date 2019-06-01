package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.flow.FlowInfo;
import com.gyfish.formflow.domain.form.FormInfo;

import java.util.List;

/**
 * @author geyu
 */
public interface FlowDao {

    Integer insert(FlowInfo flowInfo);

    FlowInfo findById(Integer id);

    List<FlowInfo> findAll();

}
