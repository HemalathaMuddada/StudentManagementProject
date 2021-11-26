package com.project.sms.email;

import org.springframework.context.ApplicationEvent;

import com.project.sms.model.User;

public class UserCreatedEvent extends ApplicationEvent {
    public UserCreatedEvent(User user) {
        super(user);
    }
}