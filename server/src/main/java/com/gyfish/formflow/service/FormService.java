package com.gyfish.formflow.service;

import com.gyfish.formflow.domain.form.FormMeta;
import com.gyfish.formflow.vo.FormQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author geyu
 */
@Slf4j
@Service
public class FormService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public FormService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<FormMeta> getList(FormQuery formQuery) {

        Criteria criteria = Criteria.where("appId").is(formQuery.getAppId());
        Query query = new Query(criteria);

        return mongoTemplate.find(query, FormMeta.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveForm(FormMeta vo) {

        log.info("save form in mongo...");

        if (vo.getId() != null) {
            mongoTemplate.remove(vo);
        } else {
            vo.setCreateTime(new Date());
        }

        vo.setUpdateTime(new Date());

        mongoTemplate.save(vo);
    }

    public void deleteForm(String id) {

        FormMeta meta = new FormMeta();
        meta.setId(id);

        mongoTemplate.remove(meta);
    }

    public FormMeta findById(String id) {

        return mongoTemplate.findById(id, FormMeta.class);
    }
}
