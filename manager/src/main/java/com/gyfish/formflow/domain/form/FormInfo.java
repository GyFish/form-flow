package com.gyfish.formflow.domain.form;

import java.util.Date;

import lombok.Data;

/**
 * @author geyu
 */
@Data
public class FormInfo {

    private Integer id;

    private String uuid;

    private String title;

    private Date createTime;

    private Date updateTime;

}
