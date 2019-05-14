package com.gyfish.formflow.controller;

import com.gyfish.formflow.util.AppResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 流程相关接口控制器
 *
 * @author geyu
 */
@RestController
@RequestMapping("/flow")
@Slf4j
public class FlowController {

    @GetMapping("/")
    public String home() {
        return "hello, i am ok!";
    }

    /**
     * 保存流程定义
     */
    @PostMapping("/saveDefinition")
    public Object saveDefinition() {

        // 保存 g6 流程图

        // 保存各个节点

        return new AppResponse<>().ok("");
    }

}
