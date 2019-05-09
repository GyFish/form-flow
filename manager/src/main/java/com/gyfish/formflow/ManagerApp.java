package com.gyfish.formflow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geyu1
 */
@SpringBootApplication
@MapperScan("com.gyfish.formflow.dao")
public class ManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class, args);
    }

}
