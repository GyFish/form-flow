package com.gyfish.formflow.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Form {

    private Integer id;

    private String title;

    private Date createdAt;

    private Date updatedAt;

}
