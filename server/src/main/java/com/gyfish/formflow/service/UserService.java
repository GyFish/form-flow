package com.gyfish.formflow.service;

import com.gyfish.formflow.dao.AppUserMapper;
import com.gyfish.formflow.domain.AppUser;
import com.gyfish.formflow.util.BeanUtil;
import com.gyfish.formflow.vo.UserQuery;
import com.gyfish.formflow.vo.UserVo;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户
 *
 * @author geyu
 */
@Service
@Slf4j
public class UserService {

    @Resource
    private AppUserMapper userMapper;

    public Object addUser(UserVo userVo) {

        AppUser user = BeanUtil.copy(userVo, AppUser.class);

        return userMapper.insert(user);
    }

    public List<AppUser> userList(UserQuery userQuery) {

        return userMapper.userList(userQuery);
    }

    public Integer deleteUser(Integer id) {

        return userMapper.deleteUser(id);
    }
}
