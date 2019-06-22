package com.gyfish.formflow.vo;

import java.util.Map;

import lombok.Data;

/**
 * @author geyu
 */
@Data
public class TaskStartVo {

    private String userId;

    private String flowId;

    private String flowName;

    private String formId;

    private Map formData;

}
