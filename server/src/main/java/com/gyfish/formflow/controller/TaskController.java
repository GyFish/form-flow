package com.gyfish.formflow.controller;

import com.gyfish.formflow.service.TaskService;
import com.gyfish.formflow.util.AppResponse;
import com.gyfish.formflow.vo.TaskStartVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Object start(TaskStartVo startVo) {

        taskService.start(startVo);

        return new AppResponse<>().ok("ok！");
    }

}
