package com.gyfish.api.controller.vo;

import com.gyfish.api.client.vo.FormInfo;
import com.gyfish.api.client.vo.FormMeta;

import java.util.List;

import lombok.Data;

/**
 * @author geyu
 */
@Data
public class FormVo {

    private String uuid;

    private FormInfo form;

    private List<FormMeta> items;

}
