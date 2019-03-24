package com.gyfish.formflow.flow;

import com.gyfish.formflow.flow.event.FlowEvent;

/**
 * 流程每一步动作的监听器，对于 flow 进行响应
 */
public interface Flowable {

    void flow(FlowEvent e);

}
