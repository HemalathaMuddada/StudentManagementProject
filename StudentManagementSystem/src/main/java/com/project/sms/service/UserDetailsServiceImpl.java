package com.project.sms.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sms.mail.EmailService;
import com.project.sms.mail.MailData;

import com.project.sms.model.PasswordGenerator;
//import com.project.sms.email.UserCreatedEvent;
import com.project.sms.model.RoleType;
import com.project.sms.model.User;
import com.project.sms.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService,UserService {
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
  

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordGenerator passwordGenerator;
	
	@Autowired
	private EmailService emailService;

@Autowired
private  ApplicationEventPublisher publisher;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =repository.findByUsername(username);

		if (user != null) {
			return user;
		}

		throw new UsernameNotFoundException(username);
	}
	

	@Override
    @Transactional(readOnly = true)
	public List<User> getAll() {

		return repository.findAll();
	}

	@Override
	@Transactional
	public void create(User user)throws Exception {
	        User userWithDuplicateUsername = repository.findByUsername(user.getUsername());
	        if(userWithDuplicateUsername != null && user.getId() != userWithDuplicateUsername.getId()) {
	            log.error(String.format("Duplicate username %", user.getUsername()));
	            throw new RuntimeException("Duplicate username.");
	        }
	        User user1 = new User();
	        user1.setEmail(user.getEmail());
	        user1.setFirstName(user.getFirstName());
	        user1.setLastName(user.getLastName());
	        user1.setUsername(user.getUsername());
	        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	        String pass=passwordGenerator.generateRandomPassword(8);
	        String encodedPassword = passwordEncoder.encode(pass);
	        System.out.println(pass);
	        
	        user.setPassword(encodedPassword);
	        
	  repository.save(user);
	  
	  MailData mail = new MailData();
			 mail.setSubject("Welcome to Student Management System Program");
	  mail.setToEmail(user.getEmail());
	  mail.setContent("You were added by " +"Username :"+user.getUsername() +"\n"+ "password :"+pass);
	  emailService.sendEmail(mail);
		
	}

	public UserDetailsServiceImpl() {
		super();
	}


	@Override
	@Transactional
	public User update(User user) {

		return repository.save(user);
	}
	//Deleting Details........
	@Override
	@Transactional
	public void delete(int id) {
	repository.deleteById(id);
	}
	



}
