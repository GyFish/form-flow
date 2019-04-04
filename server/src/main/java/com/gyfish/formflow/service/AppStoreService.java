package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.AppDao;
import com.gyfish.formflow.vo.AppInfoVo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AppStoreService {

    @Resource
    private AppDao appDao;


    public Object findAll() {

        return appDao.findAll();
    }

    public void saveApp(AppInfoVo infoVo) {

        appDao.saveApp(infoVo);
    }

    public void deleteApp(Integer appId) {

        appDao.deleteApp(appId);
    }
}
