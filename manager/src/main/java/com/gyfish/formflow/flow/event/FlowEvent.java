package com.gyfish.formflow.flow.event;

import lombok.Data;

/**
 * 通过事件触发状态的变更，相当于 vo 的对象，作为 flow action 的参数
 *
 * @author geyu
 */
@Data
public class FlowEvent {

    private int processId;

    private int actionId;

}
