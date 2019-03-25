package com.gyfish.formflow.flow.config;

import com.gyfish.formflow.flow.event.FlowEvent;
import com.gyfish.formflow.flow.filter.FlowFilterChain;
import com.gyfish.formflow.flow.filter.GlobalFlowFilter;

import lombok.Data;

@Data
public class FlowNode {

    private int processId;

    private int x;

    private int y;

    // 每个节点都有对应的状态
    private FlowStatus status;

    private String handlerId;

    private String handlerName;


    public Task run(FlowAction action, FlowEvent e) {


        // 过滤
        new FlowFilterChain(action)
                .addFilter(new GlobalFlowFilter())
                .doFilter(e);

        // 回调监听器

        // 触发

        // 回调各个

        return null;
    }
}
