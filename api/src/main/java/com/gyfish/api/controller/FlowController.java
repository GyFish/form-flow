package com.gyfish.api.controller;

import com.gyfish.api.client.ManagerClient;
import com.gyfish.api.client.ServiceClient;
import com.gyfish.api.client.vo.FlowInfo;
import com.gyfish.api.controller.vo.FlowVo;
import com.gyfish.api.util.AppResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

/**
 * @author geyu
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class FlowController {

    private final ManagerClient managerClient;

    private final ServiceClient serviceClient;

    @Autowired
    public FlowController(ManagerClient managerClient, ServiceClient serviceClient) {
        this.managerClient = managerClient;
        this.serviceClient = serviceClient;
    }

    /**
     * 代理 service-form，同时维护管理端
     */
    @PostMapping("/saveFlow")
    public Object saveForm(@RequestBody FlowVo flowVo) {

        log.info("\n>> /saveFlow");

        FlowInfo info = new FlowInfo();

        flowVo.setUuid(UUID.randomUUID().toString());

        BeanUtils.copyProperties(flowVo, info);

        // manager 保存流程节点等结构数据
        managerClient.saveFlowInfo(info);

        // service 保存图数据
        serviceClient.saveFlowMeta(flowVo);

        return new AppResponse<>().ok("save flow ok!");
    }

}
