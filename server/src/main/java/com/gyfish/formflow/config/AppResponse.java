package com.gyfish.formflow.config;

import lombok.Data;

@Data
public class AppResponse {

    private int code;
    private String msg;
    private Object data;

    AppResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static AppResponse ok(Object data) {
        return new AppResponse(0, "ok", data);
    }

}
