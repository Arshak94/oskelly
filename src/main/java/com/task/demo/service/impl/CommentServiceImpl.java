package com.task.demo.service.impl;

import com.task.demo.dto.CommentDto;
import com.task.demo.entity.Comment;
import com.task.demo.exception.CommentNotSavedException;
import com.task.demo.logic.BusinessLogic;
import com.task.demo.repo.CommentRepository;
import com.task.demo.response.CommentResponse;
import com.task.demo.service.CommentService;
import com.task.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public CommentResponse addComment(CommentDto commentDto) {
        CommentResponse response = new CommentResponse();
        Comment newComment = new Comment(commentDto.getComment());
        Comment savedComment = commentRepository.save(newComment);
        try {
            BusinessLogic.doSomeWorkOnCommentCreation();
            notificationService.addNotification(savedComment);
        } catch (RuntimeException ex) {
            System.out.println("Fail id:" + savedComment.getId());
            commentRepository.delete(savedComment);
            throw new CommentNotSavedException(savedComment.getId(),"Comment not saved");
        }
        return map(response, savedComment);
    }

    @Override
    public Page<CommentResponse> getALl(Pageable pageable) {
        Page<Comment> comments = commentRepository.findAll(pageable);
        return new PageImpl<>(comments.getContent().stream().map(c -> map(new CommentResponse(), c)).collect(Collectors.toList()), pageable, comments.getTotalElements());
    }

    private CommentResponse map(CommentResponse commentResponse, Comment comment) {
        commentResponse.setComment(comment.getComment());
        commentResponse.setId(comment.getId());
        commentResponse.setTime(comment.getTime());
        return commentResponse;
    }
}
