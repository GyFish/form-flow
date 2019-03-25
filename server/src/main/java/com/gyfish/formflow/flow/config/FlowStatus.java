package com.gyfish.formflow.flow.config;

import lombok.Getter;

/**
 * 流程
 */
@Getter
public enum FlowStatus {

    NEW(1, "新建"),
    TODO(2, "待处理"),
    DOING(3, "处理中"),
    TOCHECK(4, "待审核");

    FlowStatus(int statusValue, String statusLabel) {
        this.statusValue = statusValue;
        this.statusLabel = statusLabel;
    }

    private int processId;

    private int statusValue;

    private String statusLabel;

}
