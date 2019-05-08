package com.gyfish.formflow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gyfish.formflow.dao")
public class FormFlowApp {

    public static void main(String[] args) {
        SpringApplication.run(FormFlowApp.class, args);
    }

}
