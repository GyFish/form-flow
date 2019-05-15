package com.gyfish.api.controller;

import com.gyfish.api.client.FormClient;
import com.gyfish.api.client.ManagerClient;
import com.gyfish.api.client.vo.FormInfo;
import com.gyfish.api.controller.vo.FormVo;
import com.gyfish.api.util.AppResponse;

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
public class FormController {

    private final ManagerClient managerClient;

    private final FormClient formClient;

    @Autowired
    public FormController(ManagerClient managerClient, FormClient formClient) {
        this.managerClient = managerClient;
        this.formClient = formClient;
    }

    /**
     * 代理 service-form，同时维护管理端
     */
    @PostMapping("/saveForm")
    public Object saveForm(@RequestBody FormVo formVo) {

        log.info("\n>> /saveForm");

        String uuid = UUID.randomUUID().toString();

        log.info("set uuid = {}", uuid);
        formVo.setUuid(uuid);
        formVo.getForm().setUuid(uuid);

        // manager 添加记录，生成 uuid
        FormInfo info = formVo.getForm();
        managerClient.saveFormInfo(info);

        // service-form 带着 uuid，保存数据
        formClient.saveFormMeta(formVo);

        return new AppResponse<>().ok("save form ok!");
    }

}
