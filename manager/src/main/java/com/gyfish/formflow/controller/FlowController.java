package com.gyfish.formflow.controller;

import com.alibaba.fastjson.JSON;
import com.gyfish.formflow.domain.flow.FlowInfo;
import com.gyfish.formflow.service.FlowService;
import com.gyfish.formflow.util.AppResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private final FlowService flowService;

    @Autowired
    public FlowController(FlowService flowService) {
        this.flowService = flowService;
    }

    /**
     * 保存流程定义
     */
    @PostMapping("/saveFlowInfo")
    public Object saveFLowInfo(@RequestBody FlowInfo flowInfo) {

        log.info("\n>> 保存流程信息，flowInfo = {}", JSON.toJSONString(flowInfo, true));

        flowService.save(flowInfo);

        log.info("保存流程信息成功，id = {}", flowInfo.getId());

        return new AppResponse<>().ok("save flow info ok！");
    }

}
