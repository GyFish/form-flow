package com.gyfish.formflow.vo;

import java.util.List;

import lombok.Data;

/**
 * 新建用户的 vo，对应 com.gyfish.formflow.domain.User
 *
 * @author geyu
 */
@Data
public class UserVo {

    private String id;

    private String appId;

    private String userNo;

    private String userName;

    private List<String> flowList;

}
