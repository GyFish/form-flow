package com.gyfish.formflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowController {

    @GetMapping("/flow/")
    public String home() {
        return "hello, i am ok!";
    }

}
