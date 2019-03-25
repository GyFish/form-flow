package com.gyfish.formflow.flow.config;

import com.gyfish.formflow.flow.event.FlowEvent;
import com.gyfish.formflow.flow.filter.FlowFilter;
import com.gyfish.formflow.flow.filter.FlowFilterChain;
import com.gyfish.formflow.flow.filter.GlobalFlowFilter;
import com.gyfish.formflow.flow.listener.FlowListener;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 流程的一步动作，包含过滤器和监听器，以及规则
 */
@Data
public class FlowAction {

    private int id;

    private int processId;

    // 动作名称
    private String actionName;

    FlowNode sourceNode;

    FlowNode targetNode;


    private List<FlowListener> listeners = new ArrayList<>();


    public void start(FlowEvent e) {

    }
}
