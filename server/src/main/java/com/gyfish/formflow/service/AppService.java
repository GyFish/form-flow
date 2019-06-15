package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.AppMapper;
import com.gyfish.formflow.vo.AppInfoVo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author geyu
 */
@Service
public class AppService {

    @Resource
    private AppMapper appMapper;


    public Object findAll() {

        return appMapper.findAll();
    }

    public void saveApp(AppInfoVo infoVo) {

        appMapper.saveApp(infoVo);
    }

    public void deleteApp(Integer appId) {

        appMapper.deleteApp(appId);
    }
}
