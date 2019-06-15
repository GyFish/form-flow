package com.gyfish.formflow.domain.flow;

import com.gyfish.formflow.domain.flow.FlowNode;

import lombok.Data;

/**
 * 流程的一步动作，包含过滤器和监听器，以及规则
 *
 * @author geyu
 */
@Data
public class FlowAction {

    private int id;

    private int processId;

    private String actionName;

    FlowNode sourceNode;

    FlowNode targetNode;

}
