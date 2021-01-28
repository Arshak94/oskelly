package com.task.demo.exception;

public class CommentNotSavedException extends RuntimeException{
    private long id;

    public CommentNotSavedException() {
    }

    public CommentNotSavedException(long id, String s) {
        this(s);
        this.id = id;
    }

    public CommentNotSavedException(String s) {
        super(s);
    }

    public CommentNotSavedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CommentNotSavedException(Throwable throwable) {
        super(throwable);
    }

    public CommentNotSavedException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }

    public long getId() {
        return id;
    }
}
