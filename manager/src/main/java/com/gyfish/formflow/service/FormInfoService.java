package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.FormInfoMapper;
import com.gyfish.formflow.domain.form.FormInfo;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author geyu
 */
@Service
public class FormInfoService {

    @Resource
    private FormInfoMapper formInfoMapper;


    public List<FormInfo> getFormList() {

        return formInfoMapper.findAll();
    }

    public Integer insert(FormInfo formInfo) {

        return formInfoMapper.insert(formInfo);
    }

    public FormInfo findById(Integer id) {

        return formInfoMapper.findById(id);
    }
}
