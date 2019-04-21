package com.gyfish.formflow.domain.flow.definition;

import lombok.Getter;

/**
 * 流程
 * @author geyu
 */
@Getter
public enum FlowStatus {

    /**
     * 新建
     */
    NEW(1, "新建"),

    /**
     * 待处理
     */
    TODO(2, "待处理"),

    /**
     * 处理中
     */
    DOING(3, "处理中"),

    /**
     * 待审核
     */
    TOCHECK(4, "待审核");

    FlowStatus(int statusValue, String statusLabel) {
        this.statusValue = statusValue;
        this.statusLabel = statusLabel;
    }

    private int processId;

    private int statusValue;

    private String statusLabel;

}
