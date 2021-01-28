package com.task.demo.handler;

import com.task.demo.error.ErrorResource;
import com.task.demo.exception.CommentNotSavedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResource entityNotFoundExceptionHandler(CommentNotSavedException ex){
        return new ErrorResource(ex.getId(),"400", ex.getMessage());
    }

}
