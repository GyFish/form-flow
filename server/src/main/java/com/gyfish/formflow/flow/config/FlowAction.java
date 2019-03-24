package com.gyfish.formflow.flow.config;

import com.gyfish.formflow.flow.event.FlowEvent;
import com.gyfish.formflow.flow.filter.FlowFilter;
import com.gyfish.formflow.flow.Flowable;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class FlowAction implements Flowable {

    // 动作名称
    private String actionName;

    FlowStatus fromNode;

    FlowStatus toNode;

    private List<Flowable> listeners = new ArrayList<>();

    void addListeners(Flowable flowable) {
        this.listeners.add(flowable);
    }

    private List<FlowFilter> filters = new ArrayList<>();

    void addFilters(FlowFilter filter) {
        this.filters.add(filter);
    }

    @Override
    public void flow(FlowEvent e) {

        // 查询对应的过滤器

        // 通知监听器

        // 执行触发器

    }
}
