package com.gyfish.formflow.domain.flow;

import org.springframework.data.annotation.Id;

import java.util.Date;

import lombok.Data;

/**
 * 流程，运营管理
 *
 * @author geyu
 */
@Data
public class Process {

    @Id
    private String id;

    private String flowId;

    private String status;

    private String creator;

    private String handler;

    private String closer;

    private Date createTime;

    private Date closeTime;

}
