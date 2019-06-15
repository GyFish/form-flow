package com.gyfish.formflow.flow.listener;

import com.gyfish.formflow.flow.event.FlowEvent;

/**
 * 流程动作（action）的监听器，同时实现触发器功能
 */
public interface FlowListener {

    void flow(FlowEvent e);

}
