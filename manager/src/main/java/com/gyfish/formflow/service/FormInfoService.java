package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.FormInfoMapper;
import com.gyfish.formflow.domain.form.FormInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FormInfoService {

    @Resource
    private FormInfoMapper formInfoMapper;


    public Object getFormList() {

        return null;
    }

    public Integer insert(FormInfo formInfo) {

        return formInfoMapper.insert(formInfo);
    }

    public FormInfo findById(Integer id) {

        return formInfoMapper.findById(id);
    }
}
