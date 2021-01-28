package com.task.demo.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResource {
    @JsonProperty("id")
    private long id;
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;

    public ErrorResource() {
    }

    public ErrorResource(long id, String code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
