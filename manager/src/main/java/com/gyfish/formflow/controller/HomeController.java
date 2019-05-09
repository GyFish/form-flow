package com.gyfish.formflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author geyu1
 */
@RestController
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {

        log.info("/-- hello, i am ok!");
        return "hello, i am ok!";
    }

}
