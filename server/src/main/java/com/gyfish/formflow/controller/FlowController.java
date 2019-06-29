package com.gyfish.formflow.controller;

import com.alibaba.fastjson.JSON;
import com.gyfish.formflow.domain.flow.Flow;
import com.gyfish.formflow.service.FlowService;
import com.gyfish.formflow.util.AppResponse;
import com.gyfish.formflow.vo.FlowQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/save")
    public Object save(@RequestBody Flow flow) {

        log.info("\n>> 保存流程信息，flow = {}", JSON.toJSONString(flow, true));

        flowService.save(flow);

        return new AppResponse<>().ok("save flow ok！");
    }

    @DeleteMapping("/delete")
    public Object delete(String id) {

        flowService.delete(id);

        return new AppResponse<>().ok("delete flow ok！");
    }

    @PostMapping("/getList")
    public Object getList(@RequestBody FlowQuery flowQuery) {

        return new AppResponse<>().ok(flowService.getList(flowQuery));
    }

    @GetMapping("/getByUser")
    public Object getByUser(String userId) {

        List<Flow> flows = flowService.getByUser(userId);

        return new AppResponse<>().ok(flows);
    }

}
