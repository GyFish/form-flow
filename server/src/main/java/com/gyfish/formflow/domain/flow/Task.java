package com.gyfish.formflow.domain.flow;

import org.springframework.data.annotation.Id;

import java.util.Map;

import lombok.Data;

/**
 * 流程的一个任务，描述当前到了哪个节点，该由谁处理
 *
 * @author geyu
 */
@Data
public class Task {

    @Id
    private String id;

    private String processId;

    private String formId;

    private String userId;

    private String userGroupId;

    private String taskName;

    private Map formData;

}
