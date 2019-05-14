package com.gyfish.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geyu
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "this is api gateway";
    }

}
