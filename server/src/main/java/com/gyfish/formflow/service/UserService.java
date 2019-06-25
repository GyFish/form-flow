package com.gyfish.formflow.service;

import com.gyfish.formflow.domain.User;
import com.gyfish.formflow.util.BeanUtil;
import com.gyfish.formflow.vo.UserQuery;
import com.gyfish.formflow.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户
 *
 * @author geyu
 */
@Service
@Slf4j
public class UserService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Object addUser(UserVo userVo) {

        User user = BeanUtil.copy(userVo, User.class);

        if (user.getId() != null) {
            mongoTemplate.remove(user);
        }

        user.setCreateTime(new Date());

        return mongoTemplate.save(user);
    }

    public List<User> userList(UserQuery userQuery) {

        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        Criteria criteria = Criteria.where("appId").is(userQuery.getAppId());

        Query query = new Query(criteria).with(sort);

        return mongoTemplate.find(query, User.class);
    }

    public void deleteUser(String id) {

        User user = new User();
        user.setId(id);

        mongoTemplate.remove(user);
    }
}
