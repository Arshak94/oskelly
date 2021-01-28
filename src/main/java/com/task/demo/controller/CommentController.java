package com.task.demo.controller;

import com.task.demo.dto.CommentDto;
import com.task.demo.response.CommentResponse;
import com.task.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping()
    public Page<CommentResponse> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return commentService.getALl(PageRequest.of(page, size));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse addComment(@RequestBody CommentDto commentDto) {
        return commentService.addComment(commentDto);
    }

}