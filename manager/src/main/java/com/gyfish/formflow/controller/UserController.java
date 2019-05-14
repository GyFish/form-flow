package com.gyfish.formflow.controller;

import com.gyfish.formflow.util.AppResponse;
import com.gyfish.formflow.service.UserService;
import com.gyfish.formflow.vo.UserQuery;
import com.gyfish.formflow.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 *
 * @author geyu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 新增用户
     */
    @PostMapping("/addUser")
    public Object addUser(@RequestBody UserVo userVo) {

        return new AppResponse<>().ok("新建用户成功！", userService.addUser(userVo));
    }

    /**
     * 获取用户列表
     */
    @PostMapping("/userList")
    public Object userList(@RequestBody UserQuery userQuery) {

        return new AppResponse<>().ok(userService.userList(userQuery));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/deleteUser")
    public Object deleteUser(Integer id) {

        return new AppResponse<>().ok("删除用户成功！", userService.deleteUser(id));
    }

}
