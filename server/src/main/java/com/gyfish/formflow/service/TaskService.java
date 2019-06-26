package com.gyfish.formflow.service;

import com.alibaba.fastjson.JSONObject;
import com.gyfish.formflow.domain.flow.FlowNode;
import com.gyfish.formflow.domain.flow.FlowStatus;
import com.gyfish.formflow.domain.flow.Process;
import com.gyfish.formflow.domain.flow.Task;
import com.gyfish.formflow.util.BeanUtil;
import com.gyfish.formflow.vo.TaskQuery;
import com.gyfish.formflow.vo.TaskVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    public void commit(TaskVo vo) {

        // 生成 process
        String processId = vo.getProcessId();
        String processName = vo.getProcessName();
        if (StringUtils.isEmpty(processId)) {
            Process p = new Process();
            p.setFlowId(vo.getFlowId());
            p.setProcessName(vo.getFlowTitle());
            p.setCreator(vo.getUserId());
            p.setCreateTime(new Date());
            p.setUpdateTime(new Date());
            p.setStatus(FlowStatus.NEW.getStatusValue());
            mongoTemplate.save(p);
            processId = p.getId();
            processName = p.getProcessName();
        }

        // 更新当前 task
        Task done = BeanUtil.copy(vo, Task.class);
        if (StringUtils.isEmpty(done.getId())) {
            done.setProcessId(processId);
            done.setProcessName(processName);
            done.setUserId(vo.getUserId());
            done.setCreateTime(new Date());
        }
        done.setUpdateTime(new Date());
        done.setStatus("DONE");
        mongoTemplate.save(done);

        // 生成下一个 task
        FlowNode node = flowService.next(vo.getFlowId(), vo.getNodeId());
        if (node != null) {
            Task next = new Task();
            next.setProcessId(processId);
            next.setProcessName(processName);
            next.setPreviousId(done.getId());
            next.setTaskName(node.getNodeName());
            next.setFormId(node.getFormId());
            next.setUserId(node.getHandlerId());
            next.setStatus("TODO");
            next.setCreateTime(new Date());
            next.setUpdateTime(new Date());
            mongoTemplate.save(next);
        }
    }

    public List<Task> query(TaskQuery vo) {

        JSONObject criteria = new JSONObject();

        if (vo.getUserId() != null) {
            criteria.put("userId", vo.getUserId());
        }
        if (vo.getStatus() != null) {
            criteria.put("status", vo.getStatus());
        }
        if (vo.getProcessId() != null) {
            criteria.put("processId", vo.getProcessId());
        }

        Sort sort = new Sort(Sort.Direction.DESC, "createTime");

        Query query = new BasicQuery(criteria.toJSONString()).with(sort);

        return mongoTemplate.find(query, Task.class);
    }

    public Task getById(String id) {
        return mongoTemplate.findById(id, Task.class);
    }

    public Task previous(String taskId) {

        Task task = mongoTemplate.findById(taskId, Task.class);

        if (task == null) {
            return null;
        }

        return mongoTemplate.findById(task.getPreviousId(), Task.class);
    }
}
