package com.gyfish.formflow.flow.config;

import lombok.Data;

@Data
public class FlowNode {

    // 每个节点都有对应的状态
    private FlowStatus status;

    private String handlerId;

    private String handlerName;



}
