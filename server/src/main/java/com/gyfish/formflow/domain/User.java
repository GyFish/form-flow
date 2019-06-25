package com.gyfish.formflow.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 应用的用户
 *
 * @author geyu
 */
@Data
@Document("user")
public class User {

    @Id
    private String id;

    private String appId;

    private String userNo;

    private String userName;

    private Date createTime;

    private List<String> flowList;

}
