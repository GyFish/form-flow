package com.gyfish.formflow.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author geyu
 */
@Data
@Document
public class App {

    @Id
    private String id;

    private String title;

    private String description;

}
