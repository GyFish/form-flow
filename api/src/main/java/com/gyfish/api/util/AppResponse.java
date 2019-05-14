package com.gyfish.api.util;

import lombok.Data;

/**
 * @author geyu
 */
@Data
public class AppResponse<T> {

    private int code;
    private String msg;
    private T data;

    public AppResponse() {
    }

    public AppResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public AppResponse<T> ok() {
        return new AppResponse<>(0, "ok", null);
    }

    public AppResponse<T> ok(T data) {
        return new AppResponse<>(0, "ok", data);
    }

    public AppResponse<T> ok(String msg, T data) {
        return new AppResponse<>(0, msg, data);
    }

}
