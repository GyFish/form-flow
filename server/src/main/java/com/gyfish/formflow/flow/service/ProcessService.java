package com.gyfish.formflow.flow.service;

import com.gyfish.formflow.flow.Flowable;
import com.gyfish.formflow.flow.Process;
import com.gyfish.formflow.flow.config.FlowAction;
import com.gyfish.formflow.flow.event.FlowEvent;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProcessService {

    // 事件对应的动作
    private Map<FlowEvent, Flowable> eventMap;


    public Process getProcessById(int id) {

        return null;
    }

    public FlowAction getAction(FlowEvent e) {
        return null;
    }
}
