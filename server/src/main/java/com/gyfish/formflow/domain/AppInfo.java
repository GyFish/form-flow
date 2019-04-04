package com.gyfish.formflow.domain;

import lombok.Data;

@Data
public class AppInfo {

    private Integer id;

    private Integer formId;

    private Integer processId;

    private String title;

    private String description;

}
