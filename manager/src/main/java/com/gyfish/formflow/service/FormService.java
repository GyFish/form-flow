package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.FormDao;
import com.gyfish.formflow.domain.form.FormInfo;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/**
 * @author geyu
 */
@Service
public class FormService {

    @Resource
    private FormDao formDao;


    public List<FormInfo> getFormList() {

        return formDao.findAll();
    }

    public void save(FormInfo formInfo) {

        formDao.insert(formInfo);
    }

    public FormInfo findById(Integer id) {

        return formDao.findById(id);
    }
}
