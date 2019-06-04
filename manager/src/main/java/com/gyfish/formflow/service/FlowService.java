package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.FlowInfoMapper;
import com.gyfish.formflow.dao.FlowNodeMapper;
import com.gyfish.formflow.domain.flow.FlowInfo;
import com.gyfish.formflow.domain.flow.FlowNode;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author geyu
 */
@Service
@Slf4j
public class FlowService {

    @Resource
    private FlowInfoMapper infoMapper;

    @Resource
    private FlowNodeMapper nodeMapper;

    @Transactional(rollbackFor = Exception.class)
    public void save(FlowInfo flowInfo) {

        infoMapper.insertFlowInfo(flowInfo);

        List<FlowNode> nodes = flowInfo.getNodes();

        // 设置链表
        for (int i = 0; i < nodes.size(); i++) {
            FlowNode node = nodes.get(i);
            node.setUuid(flowInfo.getUuid());
            if (i < nodes.size() - 1) {
                FlowNode nextNode = nodes.get(i + 1);
                node.setNextNodeId(nextNode.getId());
            } else {
                node.setNextNodeId("end");
            }
        }

        nodeMapper.insertFlowNodeBatch(flowInfo.getNodes());

    }

    public List<FlowNode> getNodeList(String uuid) {

        return nodeMapper.getNodeList(uuid);
    }

    public List<FlowInfo> getFlowInfoList() {

        return infoMapper.findAll();
    }
}
