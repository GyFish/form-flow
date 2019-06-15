package com.gyfish.formflow.domain.flow;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 流程状态
 *
 * @author geyu
 */
@Data
@AllArgsConstructor
public class FlowStatus {

    public static final FlowStatus NEW = new FlowStatus("NEW", "新建");

    public static final FlowStatus TODO = new FlowStatus("TODO", "待处理");

    public static final FlowStatus DOING = new FlowStatus("DOING", "处理中");

    public static final FlowStatus TOCHECK = new FlowStatus("TOCHECK", "待审批");

    public static final FlowStatus CHECKED = new FlowStatus("CHECKED", "已审批");
    

    private String statusValue;

    private String statusLabel;

}
