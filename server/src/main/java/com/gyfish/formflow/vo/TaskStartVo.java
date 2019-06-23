package com.gyfish.formflow.vo;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * @author geyu
 */
@Data
public class TaskStartVo {

    private String userId;

    private String flowId;

    private String nodeId;

    private String taskName;

    private String formId;

    private List<Map> formData;

}
