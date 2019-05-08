package com.gyfish.formflow.domain;

import lombok.Data;

/**
 * 应用的用户
 *
 * @author geyu
 */
@Data
public class AppUser {

    private Integer id;

    /**
     * 用户工号
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

}
