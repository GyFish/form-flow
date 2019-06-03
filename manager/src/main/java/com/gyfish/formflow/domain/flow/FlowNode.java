package com.gyfish.formflow.domain.flow;

import lombok.Data;

/**
 * 流程节点，
 *
 * @author geyu
 */
@Data
public class FlowNode {

    private String id;

    private String nextNodeId;

    private String uuid;

    private Integer formId;

    private Integer processId;

    //--------------------------------

    private String nodeName;

    private String nodeType;

    private String status;

    private Boolean canFlowBack;

    //--------------------------------

    private String handlerId;

    private String handlerName;

    private String handlerGroupId;

    private String handlerGroupName;


}
