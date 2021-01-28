package com.task.demo.service;

import com.task.demo.entity.Comment;
import com.task.demo.entity.Notification;
import com.task.demo.response.NotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

    Notification addNotification(Comment comment);
    Page<NotificationResponse> getAll(Pageable pageable);
}
