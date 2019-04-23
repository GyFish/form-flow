package com.gyfish.formflow.domain.flow;

import lombok.Data;

/**
 * 流程的一个任务，描述当前到了哪个节点，该由谁处理
 *
 * @author geyu
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
