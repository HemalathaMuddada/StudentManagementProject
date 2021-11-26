package com.project.sms.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.project.sms.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserCreatedListener {
private User user;
    private  EmailService emailService;

    @EventListener
    
    public void UserCreatedEvent(UserCreatedEvent event) throws Exception {
        var user = (User) event.getSource();

        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
       

       Email mailData = new Email().builder()
                .mailSubject("Email confirmation")
                .mailTo(user.getEmail())
                .build();

        emailService.sendEmail(mailData);
    }
}