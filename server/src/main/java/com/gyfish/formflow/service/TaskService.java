package com.gyfish.formflow.service;

import com.alibaba.fastjson.JSONObject;
import com.gyfish.formflow.domain.flow.FlowNode;
import com.gyfish.formflow.domain.flow.FlowStatus;
import com.gyfish.formflow.domain.flow.Process;
import com.gyfish.formflow.domain.flow.Task;
import com.gyfish.formflow.util.BeanUtil;
import com.gyfish.formflow.vo.TaskStartVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 任务
 *
 * @author geyu
 */
@Service
public class TaskService {

    private final MongoTemplate mongoTemplate;

    private final FlowService flowService;

    @Autowired
    public TaskService(MongoTemplate mongoTemplate, FlowService flowService) {
        this.mongoTemplate = mongoTemplate;
        this.flowService = flowService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void start(TaskStartVo startVo) {

        // 生成 process
        Process p = new Process();
        p.setFlowId(startVo.getFlowId());
        p.setCreator(startVo.getUserId());
        p.setCreateTime(new Date());
        p.setUpdateTime(new Date());
        p.setStatus(FlowStatus.NEW.getStatusValue());
        mongoTemplate.save(p);

        // 生成 task
        Task task = new Task();
        BeanUtil.copy(startVo, task, false);
        task.setProcessId(p.getId());
        task.setStatus("DONE");
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        mongoTemplate.save(task);

        // 生成下一个 task
        FlowNode node = flowService.next(startVo.getFlowId(), startVo.getNodeId());
        Task next = new Task();
        next.setProcessId(p.getId());
        next.setPreviousId(task.getId());
        next.setTaskName(node.getNodeName());
        next.setFormId(node.getFormId());
        next.setUserId(node.getHandlerId());
        next.setStatus("TODO");
        next.setCreateTime(new Date());
        next.setUpdateTime(new Date());
        mongoTemplate.save(next);
    }

    public List<Task> getByUserAndStatus(String userId, String status) {

        JSONObject criteria = new JSONObject();

        criteria.put("userId", userId);
        criteria.put("status", status);

        Query query = new BasicQuery(criteria.toJSONString());

        return mongoTemplate.find(query, Task.class);
    }


    public Task previous(String taskId) {

        Task task = mongoTemplate.findById(taskId, Task.class);

        if (task == null) {
            return null;
        }

        return mongoTemplate.findById(task.getPreviousId(), Task.class);
    }
}
