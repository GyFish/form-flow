package com.gyfish.api.controller.vo;

import com.alibaba.fastjson.JSONObject;
import com.gyfish.api.client.vo.FlowNode;

import java.util.List;

import lombok.Data;

/**
 * @author geyu
 */
@Data
public class FlowVo {

    private String uuid;

    private String title;

    private JSONObject graph;

    private List<FlowNode> nodes;

}
