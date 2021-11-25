package com.project.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.project.sms.model.Email;
import com.project.sms.service.EmailService;

@SpringBootApplication
public class StudentManagementSystemApplication {

	public static void main(String[] args) {		
		Email mail = new Email();
        mail.setMailFrom("hemalathamuddada2@gmail.com");
        mail.setMailTo("ganeshsaikandregula03@gmail.com");
        mail.setMailSubject("Testing application-to run Email Example");
        mail.setMailContent("Working on email sending example");
 
        ApplicationContext ctx = SpringApplication.run(StudentManagementSystemApplication.class, args);
        EmailService mailService = (EmailService) ctx.getBean("mailService");
        mailService.sendEmail(mail);
	}


}
