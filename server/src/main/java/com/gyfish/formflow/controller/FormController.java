package com.gyfish.formflow.controller;

import com.alibaba.fastjson.JSON;
import com.gyfish.formflow.domain.form.FormMeta;
import com.gyfish.formflow.service.FormService;
import com.gyfish.formflow.util.AppResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    /**
     * 保存表单设计
     */
    @PostMapping("/saveForm")
    public Object saveForm(@RequestBody FormMeta vo) {

        log.info("\n>> 保存表单设计，vo = {}", JSON.toJSONString(vo, true));

        formService.saveForm(vo);

        log.info("保存表单设计成功，id = {}");

        return new AppResponse<>().ok("ok！", vo);
    }

    @DeleteMapping("/deleteForm")
    public Object deleteForm(String id) {

        log.info("\n>> 删除表单设计，id = {}", id);

        formService.deleteForm(id);

        return new AppResponse<>().ok("ok！");
    }

    /**
     * 获取表单列表
     */
    @GetMapping("/getFormList")
    public Object getFormList() {

        log.info("=== 获取表单列表 ===");

        return new AppResponse<>().ok(formService.getFormList());
    }


}
