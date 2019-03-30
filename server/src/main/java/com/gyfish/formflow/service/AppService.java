package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.AppDao;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class AppService {

    @Resource
    private AppDao appDao;


    public Object findAll() {

        List list = appDao.findAll();
        list.forEach(a -> System.out.println(a));
        return list;
    }
}
