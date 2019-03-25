package com.gyfish.formflow.flow.config;

import lombok.Data;

/**
 * 流程的一个任务，描述当前到了哪个节点，该由谁处理
 */
@Data
public class Task {

    private int id;

    private int processId;

    private String handlerId;

    private String handlerName;

    private String handlerGroupId;

    private String handlerGroupName;

}
