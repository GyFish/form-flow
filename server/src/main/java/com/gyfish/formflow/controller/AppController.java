package com.gyfish.formflow.controller;

import com.gyfish.formflow.util.AppResponse;
import com.gyfish.formflow.service.AppService;
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
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/app/appList")
    public Object appList() {

        return new AppResponse<>().ok(appService.findAll());
    }

    @PostMapping("/app/saveApp")
    public AppResponse saveApp(@RequestBody AppInfoVo infoVo) {

        appService.saveApp(infoVo);
        return new AppResponse<>().ok("save appInfo ok!");
    }


    @DeleteMapping("/app/deleteApp")
    public AppResponse deleteApp(Integer appId) {

        appService.deleteApp(appId);
        return new AppResponse<>().ok("delete appInfo ok!");
    }


}
