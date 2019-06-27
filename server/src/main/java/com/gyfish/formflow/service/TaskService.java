package com.gyfish.formflow.service;

import com.alibaba.fastjson.JSONObject;
import com.gyfish.formflow.domain.flow.FlowNode;
import com.gyfish.formflow.domain.flow.Process;
import com.gyfish.formflow.domain.flow.Task;
import com.gyfish.formflow.vo.TaskQuery;
import com.gyfish.formflow.vo.TaskVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 任务
 *
 * @author geyu
 */
@Service
@Slf4j
public class TaskService {

    private final MongoTemplate mongoTemplate;

    private final FlowService flowService;

    private final ProcessService processService;

    @Autowired
    public TaskService(MongoTemplate mongoTemplate, FlowService flowService, ProcessService processService) {
        this.mongoTemplate = mongoTemplate;
        this.flowService = flowService;
        this.processService = processService;
    }

    private Task save(Task task) {

        return mongoTemplate.save(task);
    }

    private Task saveTask(Process p, TaskVo vo) {

        Task task = new Task();

        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());

        task.setProcessId(p.getId());
        task.setProcessName(p.getProcessName());

        FlowNode node = flowService.next(p);
        if (node == null) {
            processService.end(p);
            return task;
        }

        task.setTaskName(node.getNodeName());

        String userId = vo.getUserId();
        if (userId != null) {
            task.setUserId(vo.getUserId());
            task.setStatus("DONE");
            task.setFormId(vo.getFormId());
            task.setFormData(vo.getFormData());
        }
        if (userId == null) {
            task.setFormId(node.getFormId());
            task.setUserId(node.getHandlerId());
            task.setStatus("TODO");
        }

        return this.save(task);
    }

    /**
     * 新建任务，需要参数：flowId，userId，formId，formData
     */
    public void start(TaskVo vo) {

        // 开启流程
        Process p1 = processService.start(vo);

        // 创建当前任务
        Task task = this.saveTask(p1, vo);

        // 前进一步
        Process p2 = processService.commit(task);

        // 生成下一个任务
        this.saveTask(p2, new TaskVo());
    }

    /**
     * 提交任务，需要参数：taskId，formData
     */
    public void commit(TaskVo vo) {

        // 更新当前 task
        Task task = this.updateTask(vo);

        // 更新流程状态
        Process p = processService.commit(task);

        // 生成下一个 task
        this.saveTask(p, new TaskVo());
    }

    private Task updateTask(TaskVo vo) {

        Task task = this.getById(vo.getId());

        task.setFormData(vo.getFormData());

        task.setUpdateTime(new Date());

        task.setStatus("DONE");

        return mongoTemplate.save(task);
    }


    private Task getById(String id) {
        return mongoTemplate.findById(id, Task.class);
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

    public Task previous(String taskId) {

        Task task = this.getById(taskId);

        TaskQuery query = new TaskQuery();

        query.setProcessId(task.getProcessId());

        return this.query(query).get(0);
    }
}
