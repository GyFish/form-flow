package com.gyfish.formflow.controller;

import com.gyfish.formflow.config.AppResponse;
import com.gyfish.formflow.service.AppStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppStoreController {

    private final AppStoreService appStoreService;

    @Autowired
    public AppStoreController(AppStoreService appStoreService) {
        this.appStoreService = appStoreService;
    }

    @GetMapping("/app/appList")
    public Object appList() {

        return appStoreService.findAll();
    }

    @PostMapping("/app-store/saveApp")
    public AppResponse saveApp() {

        return AppResponse.ok("新建 app ok！");
    }

}
