package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.FormMapper;
import com.gyfish.formflow.domain.form.FormInfo;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author geyu
 */
@Service
public class FormService {

    @Resource
    private FormMapper formMapper;


    public List<FormInfo> getFormList() {

        return formMapper.findAll();
    }

    public void save(FormInfo formInfo) {

        formMapper.insert(formInfo);
    }

    public FormInfo findById(Integer id) {

        return formMapper.findById(id);
    }
}
