package com.gyfish.formflow.flow;

import com.gyfish.formflow.flow.config.FlowAction;
import com.gyfish.formflow.flow.event.FlowEvent;
import com.gyfish.formflow.flow.service.ProcessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 状态机，根据事件，调度
 */
@Component
public class StatusMachine {


    final
    ProcessService processService;

    @Autowired
    public StatusMachine(ProcessService processService) {
        this.processService = processService;
    }


    public void emit(FlowEvent e) {

        if (e == null)
            return;

        FlowAction action = getAction(e);

        if (action != null)
            action.flow(e);
    }

    private FlowAction getAction(FlowEvent e) {

        return null;
    }


}
