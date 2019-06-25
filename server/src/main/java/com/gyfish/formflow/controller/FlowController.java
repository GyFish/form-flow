package com.gyfish.formflow.controller;

import com.alibaba.fastjson.JSON;
import com.gyfish.formflow.domain.flow.FlowMeta;
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
    public Object saveFLow(@RequestBody FlowMeta flowMeta) {

        log.info("\n>> 保存流程信息，flowMeta = {}", JSON.toJSONString(flowMeta, true));

        flowService.save(flowMeta);

        log.info("保存流程信息成功，id = {}", flowMeta.getId());

        return new AppResponse<>().ok("save flow ok！");
    }

    @DeleteMapping("/delete")
    public Object deleteFlow(String id) {

        log.info("\n>> 删除流程，id = {}", id);

        flowService.delete(id);

        return new AppResponse<>().ok("delete flow ok！");
    }

    @PostMapping("/getList")
    public Object getList(@RequestBody FlowQuery flowQuery) {

        return new AppResponse<>().ok(flowService.getList(flowQuery));
    }

    @GetMapping("/getByUser")
    public Object getByUser(String userId) {

        List<FlowMeta> flows = flowService.getByUser(userId);

        log.info("|getByUser| flows = {}", JSON.toJSONString(flows));

        return new AppResponse<>().ok(flows);
    }

}
