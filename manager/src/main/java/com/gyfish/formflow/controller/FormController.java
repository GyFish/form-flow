package com.gyfish.formflow.controller;

import com.alibaba.fastjson.JSON;
import com.gyfish.formflow.domain.form.FormInfo;
import com.gyfish.formflow.service.FormInfoService;
import com.gyfish.formflow.util.AppResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

/**
 * 表单接口
 *
 * @author geyu
 */
@RestController
@RequestMapping("/form")
@Slf4j
public class FormController {

    private final FormInfoService formInfoService;

    @Autowired
    public FormController(FormInfoService formInfoService) {
        this.formInfoService = formInfoService;
    }

    /**
     * 保存表单信息
     */
    @PostMapping("/saveFormInfo")
    public Object saveFormInfo(@RequestBody FormInfo formInfo) {

        log.info("=== 保存表单信息 ===");
        log.info("formInfo = {}", JSON.toJSONString(formInfo, true));

        formInfo.setUuid(UUID.randomUUID().toString());
        formInfoService.insert(formInfo);

        return new AppResponse<>().ok("保存表单结构数据成功！", formInfo);
    }

    /**
     * 获取表单列表
     */
    @GetMapping("/getFormList")
    public Object getFormList() {

        log.info("=== 获取表单列表 ===");

        return new AppResponse<>().ok(formInfoService.getFormList());
    }

    /**
     * 获取表单的具体元素
     */
    @GetMapping("/getFormItems")
    public Object getFormItems(Integer dFormId) {

        log.info("=== 获取表单元素列表 ===");
        log.info("dFormId = {}", JSON.toJSONString(dFormId));

        return new AppResponse<>().ok();
    }

}
