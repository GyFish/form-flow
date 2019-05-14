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

import reactor.core.publisher.Mono;

/**
 * @author geyu
 */
@RestController
@RequestMapping("/api")
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

        // manager 添加记录，生成 uuid
        FormInfo info = formVo.getFormInfo();
        managerClient
                .saveFormInfo(info)
                .subscribe(info::setUuid);

        // service-form 带着 uuid，保存数据
        formClient.saveFormMeta(formVo);

        return new AppResponse<>().ok("save form ok!");
    }

}
