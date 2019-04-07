package com.gyfish.formflow.controller;

import com.alibaba.fastjson.JSON;
import com.gyfish.formflow.config.AppResponse;
import com.gyfish.formflow.service.FormService;
import com.gyfish.formflow.vo.FormEditorVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/form")
@Slf4j
public class FormController {

    private final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping("/saveFormEditor")
    public Object saveFormInfo(@RequestBody FormEditorVo formEditorVo) {
        log.info("=== save form ===");
        log.info("formEditorVo = {}", JSON.toJSONString(formEditorVo, true));

        formService.saveFormEditor(formEditorVo);

        return AppResponse.ok("保存表单结构数据成功！");
    }

}
