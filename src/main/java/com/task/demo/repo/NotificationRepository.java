package com.task.demo.repo;

import com.task.demo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long> {

    public Notification findByCommentId(Long commentId);

    public long countByDelivered(boolean isDelivered);
}

