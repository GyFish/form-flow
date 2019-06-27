package com.gyfish.formflow.controller;

import com.alibaba.fastjson.JSON;
import com.gyfish.formflow.domain.flow.Task;
import com.gyfish.formflow.service.TaskService;
import com.gyfish.formflow.util.AppResponse;
import com.gyfish.formflow.vo.TaskQuery;
import com.gyfish.formflow.vo.TaskVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author geyu
 */
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/start")
    public Object start(@RequestBody TaskVo vo) {

        taskService.start(vo);

        return new AppResponse<>().ok("创建成功！");
    }

    @PostMapping("/commit")
    public Object commit(@RequestBody TaskVo vo) {

        taskService.commit(vo);

        return new AppResponse<>().ok("提交成功！");
    }

    @GetMapping("/previous")
    public Object previous(String taskId) {

        log.info("|查询上一节点| taskId = {}", taskId);

        Task task = taskService.previous(taskId);

        log.info("|上一节点| task = {}", JSON.toJSONString(task, true));

        return new AppResponse<>().ok("ok！", task);
    }

    @PostMapping("/query")
    public Object query(@RequestBody TaskQuery vo) {

        log.info("|查询task| queryVo = {}", JSON.toJSONString(vo, true));

        List<Task> result = taskService.query(vo);

        return new AppResponse<>().ok(result);
    }

}
