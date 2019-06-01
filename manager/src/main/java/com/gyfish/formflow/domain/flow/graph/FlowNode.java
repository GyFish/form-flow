package com.gyfish.formflow.domain.flow.graph;

import lombok.Data;

/**
 * 流程节点，
 *
 * @author geyu
 */
@Data
public class FlowNode {

    private String id;

    private Integer formId;

    private Integer nextNodeId;

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
