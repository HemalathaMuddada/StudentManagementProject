package com.project.sms.mail;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.sms.model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserCreatedListener {

	@Autowired
	private PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @EventListener
    @Async
    public void onUserCreated(UserCreatedEvent event) throws Exception {
        final var user = (User) event.getSource();

        Map<String, Object> model = new HashMap<>();
        model.put("reservation", user);
     

        var mailData = new MailData.MailDataBuilder()
                .subject("Welcome to Student Management System Program")
                .toEmail(user.getEmail())
                .content("You were added by " +"Username :"+user.getUsername()+" "+user.getPassword())
                .model(model)
                .build();

       emailService.sendEmail(mailData);
    }
}