package com.gyfish.formflow.dao;

import com.gyfish.formflow.domain.AppUser;
import com.gyfish.formflow.vo.UserQuery;

import java.util.List;

/**
 * @author geyu
 */
public interface UserMapper {


    /**
     * 向应用中添加用户
     *
     * @param user 用户实体
     * @return 入库的数量
     */
    Integer insert(AppUser user);

    /**
     * 查询用户
     *
     * @param userQuery 查询参数
     * @return 用户列表
     */
    List<AppUser> userList(UserQuery userQuery);

    /**
     * 根据 id 删除用户
     *
     * @param id user id
     * @return 删除的数量
     */
    Integer deleteUser(Integer id);
}
