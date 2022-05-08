package com.hrs.test.dto;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class JsonResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    private String status = null;
    private Object result = null;
    private String message = null;

    public JsonResponse() {

    }

    public JsonResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

