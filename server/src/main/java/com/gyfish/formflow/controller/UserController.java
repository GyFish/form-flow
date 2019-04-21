package com.gyfish.formflow.controller;

import com.gyfish.formflow.config.AppResponse;
import com.gyfish.formflow.service.UserService;
import com.gyfish.formflow.vo.UserQuery;
import com.gyfish.formflow.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

        return AppResponse.ok("新建用户成功！", userService.addUser(userVo));
    }

    /**
     * 获取用户列表
     */
    @PostMapping("/userList")
    public Object userList(@RequestBody UserQuery userQuery) {

        return AppResponse.ok(userService.userList(userQuery));
    }

}
