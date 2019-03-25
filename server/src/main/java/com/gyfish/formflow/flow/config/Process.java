package com.gyfish.formflow.flow.config;

import com.gyfish.formflow.flow.config.FlowNode;

import lombok.Data;

/**
 * 流程，由 点、线 组成，即节点和动作，比如一个工单的创建，就代表一个 process 的启动
 */
@Data
public class Process {

    private int id;

    // 模板
    private int type;

    // 当前所在节点
    private FlowNode curNode;



}
