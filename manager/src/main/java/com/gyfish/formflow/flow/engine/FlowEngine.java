package com.gyfish.formflow.flow.engine;

import com.gyfish.formflow.domain.flow.Task;
import com.gyfish.formflow.flow.filter.FlowFilter;
import com.gyfish.formflow.flow.filter.FlowFilterChain;
import com.gyfish.formflow.flow.filter.GlobalFlowFilter;
import com.gyfish.formflow.flow.listener.FlowListener;
import com.gyfish.formflow.domain.flow.Process;
import com.gyfish.formflow.domain.flow.graph.FlowAction;
import com.gyfish.formflow.domain.flow.graph.FlowNode;
import com.gyfish.formflow.flow.event.FlowEvent;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 引擎，我是谁，我在哪，我要做什么
 *
 * @author geyu
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
