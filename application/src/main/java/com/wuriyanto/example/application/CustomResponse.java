package com.wuriyanto.example.application;

import com.fasterxml.jackson.annotation.JsonInclude;

public class CustomResponse {

    private Integer code;
    private Boolean success;
    private Object message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meta meta;

    private Object data;

    public CustomResponse() {

    }

    public CustomResponse(Integer code, Boolean success, Object data, Object message) {
        this.code = code;
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public CustomResponse(Integer code, Boolean success, Object data, Object message, Meta meta) {
        this.code = code;
        this.success = success;
        this.data = data;
        this.message = message;
        this.meta = meta;
    }

    public Integer getCode() {
        return code;
    }

    public Boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public Object getMessage() {
        return message;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
