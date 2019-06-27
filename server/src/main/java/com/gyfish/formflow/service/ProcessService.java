package com.gyfish.formflow.service;

import com.gyfish.formflow.domain.flow.FlowMeta;
import com.gyfish.formflow.domain.flow.FlowNode;
import com.gyfish.formflow.domain.flow.FlowStatus;
import com.gyfish.formflow.domain.flow.Process;
import com.gyfish.formflow.domain.flow.Task;
import com.gyfish.formflow.vo.TaskVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 流程
 *
 * @author geyu
 */
@Service
public class ProcessService {

    private final MongoTemplate mongoTemplate;

    private final FlowService flowService;

    @Autowired
    public ProcessService(MongoTemplate mongoTemplate, FlowService flowService) {
        this.mongoTemplate = mongoTemplate;
        this.flowService = flowService;
    }

    public List<Process> getProcessList() {

        return null;
    }

    Process start(TaskVo vo) {

        Process p = new Process();

        FlowMeta meta = flowService.getById(vo.getFlowId());

        p.setFlowId(meta.getId());
        p.setProcessName(meta.getTitle());
        p.setNodeId("startNode");

        p.setCreator(vo.getUserId());

        p.setCreateTime(new Date());
        p.setUpdateTime(new Date());

        p.setStatus(FlowStatus.NEW.getStatusValue());

        return mongoTemplate.save(p);
    }

    public Process getById(String id) {

        return mongoTemplate.findById(id, Process.class);
    }

    Process getByTask(Task task) {

        return mongoTemplate.findById(task.getProcessId(), Process.class);
    }

    void end(Process p) {

        p.setStatus("END");

        mongoTemplate.save(p);
    }

    Process commit(Task task) {

        Process p = this.getByTask(task);

        FlowNode node = flowService.next(p);

        p.setNodeId(node.getId());
        p.setHandler(node.getHandlerId());
        p.setUpdateTime(new Date());

        return mongoTemplate.save(p);
    }
}
