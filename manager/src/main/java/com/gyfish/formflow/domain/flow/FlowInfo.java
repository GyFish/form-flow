package com.gyfish.formflow.domain.flow;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author geyu
 */
@Data
public class FlowInfo {

    private Integer id;

    private String uuid;

    private String title;

    private Date createTime;

    private Date updateTime;

    private List<FlowNode> nodes;

}
