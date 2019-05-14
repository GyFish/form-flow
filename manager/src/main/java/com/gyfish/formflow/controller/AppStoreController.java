package com.gyfish.formflow.controller;

import com.gyfish.formflow.util.AppResponse;
import com.gyfish.formflow.service.AppStoreService;
import com.gyfish.formflow.vo.AppInfoVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geyu
 */
@RestController
@RequestMapping("/app-store")
public class AppStoreController {

    private final AppStoreService appStoreService;

    @Autowired
    public AppStoreController(AppStoreService appStoreService) {
        this.appStoreService = appStoreService;
    }

    @GetMapping("/appList")
    public Object appList() {

        return new AppResponse<>().ok(appStoreService.findAll());
    }

    @PostMapping("/saveApp")
    public AppResponse saveApp(@RequestBody AppInfoVo infoVo) {

        appStoreService.saveApp(infoVo);
        return new AppResponse<>().ok("save appInfo ok!");
    }


    @DeleteMapping("/deleteApp")
    public AppResponse deleteApp(Integer appId) {

        appStoreService.deleteApp(appId);
        return new AppResponse<>().ok("delete appInfo ok!");
    }


}
