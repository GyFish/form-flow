package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.AppInfo;
import com.gyfish.formflow.vo.AppInfoVo;

import java.util.List;

public interface AppDao {

    List<AppInfo> findAll();

    void saveApp(AppInfoVo infoVo);

    void deleteApp(Integer appId);
}
