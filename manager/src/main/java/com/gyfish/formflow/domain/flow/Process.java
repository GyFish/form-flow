package com.gyfish.formflow.domain.flow;

import com.gyfish.formflow.domain.flow.graph.FlowNode;

import lombok.Data;

/**
 * 流程，由 点、线 组成，即节点和动作，比如一个工单的创建，就代表一个 process 的启动
 *
 * @author geyu
 */
@Data
public class Process {

    private int id;

    private int type;

    private FlowNode curNode;



}
