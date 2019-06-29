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
public class Flow {

    @Id
    private String id;

    private String appId;

    private String uuid;

    private String title;

    /**
     * todo展示，可选 firstTask / lastTask
     */
    private String todoShow;

    private Date createTime;

    private Date updateTime;

    private List<FlowNode> nodes;

}
