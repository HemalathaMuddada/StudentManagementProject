package com.project.sms.mail;

import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {
    public UserCreatedEvent(Object source) {
        super(source);
    }
}