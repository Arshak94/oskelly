package com.task.demo.response;

import java.util.Date;

public class NotificationResponse {

    Long id;

    Date time;

    boolean delivered;

    public NotificationResponse(Long id, Date time, boolean delivered) {
        this.id = id;
        this.time = time;
        this.delivered = delivered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
