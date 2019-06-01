package com.gyfish.api.client.vo;

import java.util.List;

import lombok.Data;

/**
 * @author geyu
 */
@Data
public class FlowInfo {

    private String uuid;

    private String title;

    private List<FlowNode> nodes;

}
