package com.project.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

//import com.project.sms.email.Email;
//import com.project.sms.email.EmailService;

@SpringBootApplication

public class StudentManagementSystemApplication {

	public static void main(String[] args) {		
		
 
       SpringApplication.run(StudentManagementSystemApplication.class, args);
     
	}


}
