package com.gyfish.formflow.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/form")
@Slf4j
public class FormController {

    @PostMapping("/save")
    public Object save() {
        log.info("=== save form ===");
        return null;
    }

}
