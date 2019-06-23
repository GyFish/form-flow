package com.gyfish.formflow.domain.flow;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author geyu
 */
@Data
@Document("flow")
public class FlowMeta {

    @Id
    private String id;

    private String uuid;

    private String title;

    private Date createTime;

    private Date updateTime;

    private List<FlowNode> nodes;

}
