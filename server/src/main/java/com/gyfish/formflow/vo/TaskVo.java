package com.gyfish.formflow.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * @author geyu
 */
@Data
public class TaskVo {

    private String id;

    private String taskName;

    private String userId;

    private Date createTime;


    private String processId;

    private String processName;


    private String flowId;

    private String flowTitle;

    
    private String nodeId;

    private String formId;

    private List<Map> formData;

}
