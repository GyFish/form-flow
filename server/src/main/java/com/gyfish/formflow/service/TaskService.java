package com.gyfish.formflow.service;

import com.gyfish.formflow.vo.TaskStartVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * 任务
 *
 * @author geyu
 */
@Service
public class TaskService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TaskService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void start(TaskStartVo startVo) {



    }
}
