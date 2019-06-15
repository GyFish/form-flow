package com.gyfish.formflow.service;

import com.gyfish.formflow.domain.flow.FlowMeta;
import com.gyfish.formflow.domain.flow.FlowNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author geyu
 */
@Service
@Slf4j
public class FlowService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public FlowService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(FlowMeta meta) {

        // 先删除
        String id = meta.getId();
        if (!StringUtils.isEmpty(id)) {
            mongoTemplate.remove(meta);
        }

        // 设置时间
        meta.setUpdateTime(new Date());
        if (meta.getCreateTime() == null) {
            meta.setCreateTime(new Date());
        }

        // 设置链表
        List<FlowNode> nodes = meta.getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            FlowNode node = nodes.get(i);
            node.setUuid(meta.getUuid());
            if (i < nodes.size() - 1) {
                FlowNode nextNode = nodes.get(i + 1);
                node.setNextNodeId(nextNode.getId());
            } else {
                node.setNextNodeId("end");
            }
        }

        mongoTemplate.save(meta);
    }


    public List<FlowMeta> getFlowList() {

        return mongoTemplate.findAll(FlowMeta.class);
    }

    public void deleteFlow(String id) {

        FlowMeta meta = new FlowMeta();
        meta.setId(id);

        mongoTemplate.remove(meta);
    }
}
