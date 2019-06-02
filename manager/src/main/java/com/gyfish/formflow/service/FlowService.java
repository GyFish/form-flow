package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.FlowDao;
import com.gyfish.formflow.domain.flow.FlowInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author geyu
 */
@Service
@Slf4j
public class FlowService {

    @Resource
    private FlowDao flowDao;

    public void save(FlowInfo flowInfo) {

        flowDao.insertFlowInfo(flowInfo);

        flowDao.insertFlowNodeBatch(flowInfo.getNodes());

    }

}
