package com.gyfish.api.controller;

import com.gyfish.api.client.FormClient;
import com.gyfish.api.client.ManagerClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

/**
 * @author geyu
 */
@RestController
@RequestMapping("/form")
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
    @PostMapping("/saveFormMeta")
    public Object saveFormMeta(@RequestBody Object o) {

        // manager 添加记录，生成 uuid
        Mono<String> uuid = managerClient.saveDefinition(o);

        // service-form 带着 uuid，保存数据
        return formClient.saveFormMeta(uuid);
    }

}
