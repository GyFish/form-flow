package com.gyfish.formflow.domain.flow;

import lombok.Data;

/**
 * 流程节点，
 *
 * @author geyu
 */
@Data
public class FlowNode {

    private Integer pk;

    private String id;

    private String uuid;

    private String nextNodeId;

    private String formId;

    private String processId;

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
