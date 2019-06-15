package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.FormMapper;
import com.gyfish.formflow.domain.form.FormInfo;
import com.gyfish.formflow.domain.form.FormMeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author geyu
 */
@Slf4j
@Service
public class FormService {

    @Resource
    private FormMapper formMapper;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public FormService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<FormMeta> getFormList() {

        return mongoTemplate.findAll(FormMeta.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveForm(FormMeta vo) {

        log.info("save form in mongo...");

        mongoTemplate.save(vo);
    }

    public FormInfo findById(Integer id) {

        return formMapper.findById(id);
    }

    public void deleteForm(String id) {

        FormMeta meta = new FormMeta();
        meta.setId(id);

        mongoTemplate.remove(meta);
    }
}
