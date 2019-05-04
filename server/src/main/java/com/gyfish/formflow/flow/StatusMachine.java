package com.gyfish.formflow.flow;

import com.gyfish.formflow.domain.flow.Task;
import com.gyfish.formflow.domain.flow.definition.FlowAction;
import com.gyfish.formflow.domain.flow.definition.FlowNode;
import com.gyfish.formflow.flow.engine.FlowEngine;
import com.gyfish.formflow.flow.event.FlowEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 状态机，根据事件进行引擎调度
 */
@Component
public class StatusMachine {

    private FlowEngine flowEngine;

    @Autowired
    public StatusMachine(FlowEngine flowEngine) {
        this.flowEngine = flowEngine;
    }

    /**
     * 基于事件触发流程的执行，返回对应的任务
     *
     * @param e 流程事件
     */
    public Task emit(FlowEvent e) {

        if (e == null) {
            return null;
        }

        // 获取流程的当前节点
        FlowNode node = flowEngine.getCurNode(e.getProcessId());

        // 获取事件要触发的动作
        FlowAction action = flowEngine.getActionById(e.getActionId());

        // 执行动作，返回任务
        return node.run(action, e);
    }


}
