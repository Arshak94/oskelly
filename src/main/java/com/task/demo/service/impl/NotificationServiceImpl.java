package com.task.demo.service.impl;

import com.task.demo.logic.BusinessLogic;
import com.task.demo.entity.Comment;
import com.task.demo.entity.Notification;
import com.task.demo.repo.NotificationRepository;
import com.task.demo.response.NotificationResponse;
import com.task.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification addNotification(Comment comment) {
        Notification notification = new Notification();
        notification.setTime(new Date());
        notification.setDelivered(true);
        notification.setComment(comment);
        Notification n = notificationRepository.save(notification);

        Thread t = new Thread(() -> {
            try {
                BusinessLogic.doSomeWorkOnNotification();
            } catch (RuntimeException ex) {
                n.setDelivered(false);
                notificationRepository.save(n);
            }

        });
        t.start();
        return n;
    }

    @Override
    public Page<NotificationResponse> getAll(Pageable pageable) {
        Page<Notification> notifications = notificationRepository.findAll(pageable);
        return new PageImpl<>(notifications.stream().map(this::map).collect(Collectors.toList()), pageable, notifications.getTotalElements());
    }

    private NotificationResponse map(Notification notification){
        return new NotificationResponse(notification.getId(), notification.getTime(), notification.isDelivered());
    }
}
