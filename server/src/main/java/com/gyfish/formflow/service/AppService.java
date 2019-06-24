package com.gyfish.formflow.service;

import com.gyfish.formflow.domain.App;
import com.gyfish.formflow.util.BeanUtil;
import com.gyfish.formflow.vo.AppInfoVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author geyu
 */
@Service
public class AppService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AppService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Object findAll() {

        return mongoTemplate.findAll(App.class);
    }

    public void saveApp(AppInfoVo infoVo) {

        App app = BeanUtil.copy(infoVo, App.class);

        mongoTemplate.save(app);
    }

    public void deleteApp(String appId) {

        App app = new App();
        app.setId(appId);

        mongoTemplate.remove(app);
    }
}
