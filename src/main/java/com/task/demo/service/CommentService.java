package com.task.demo.service;

import com.task.demo.dto.CommentDto;
import com.task.demo.response.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    CommentResponse addComment(CommentDto commentDto);

    Page<CommentResponse> getALl(Pageable pageable);
}
