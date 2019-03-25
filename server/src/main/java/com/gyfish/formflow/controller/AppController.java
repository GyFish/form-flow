package com.gyfish.formflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/app/appList")
    public String appList() {
        return "hello, i am ok!";
    }

}
