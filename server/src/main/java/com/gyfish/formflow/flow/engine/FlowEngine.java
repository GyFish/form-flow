package com.gyfish.formflow.flow.engine;

import com.gyfish.formflow.flow.filter.FlowFilter;
import com.gyfish.formflow.flow.listener.FlowListener;
import com.gyfish.formflow.flow.config.Process;
import com.gyfish.formflow.flow.config.FlowAction;
import com.gyfish.formflow.flow.config.FlowNode;
import com.gyfish.formflow.flow.event.FlowEvent;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 引擎？，提供基础的服务
 */
@Service
public class FlowEngine {

    // 事件对应的动作
    private Map<FlowEvent, FlowListener> eventMap;


    public Process getProcessById(int id) {

        return null;
    }

    public FlowNode getCurNode(int processId) {

        return new FlowNode();
    }

    public FlowAction getActionById(int actionId) {

        return new FlowAction();
    }

    public List<FlowFilter> getFlowFilersByAction() {

        return null;
    }




}
