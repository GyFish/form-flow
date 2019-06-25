package com.gyfish.formflow.domain.flow;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 流程的一个任务，描述当前到了哪个节点，该由谁处理
 *
 * @author geyu
 */
@Data
@Document("task")
public class Task {

    @Id
    private String id;

    private String appId;

    private String previousId;

    private String nextId;

    private String processId;

    private String processName;

    private String nodeId;

    private String formId;

    private String userId;

    private String userGroupId;

    private String taskName;

    private List<Map> formData;

    /**
     * TODO, DOING, DONE
     */
    private String status;

    private Date createTime;

    private Date updateTime;

}
