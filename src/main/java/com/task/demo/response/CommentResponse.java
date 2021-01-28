package com.task.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CommentResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("time")
    private Date time;

    public CommentResponse() {
    }

    public CommentResponse(Long id, String comment, Date time) {
        this.id = id;
        this.comment = comment;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
