package com.project.sms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.project.sms.dto.UserDto;
import com.project.sms.exceptions.CustomExceptions;
import com.project.sms.mail.EmailService;
import com.project.sms.mail.MailData;
import com.project.sms.model.Authority;
import com.project.sms.model.PasswordGenerator;
//import com.project.sms.email.UserCreatedEvent;

import com.project.sms.model.User;
import com.project.sms.repository.AuthorityRepository;
import com.project.sms.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService,UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	

	
	
  @Autowired
  private AuthorityRepository authorityRepository;

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
	public User create(UserDto user)throws Exception {
	        User userWithDuplicateUsername = repository.findByUsername(user.getUsername());
	        if(userWithDuplicateUsername != null && user.getId() != userWithDuplicateUsername.getId()) {
	            log.error(String.format("Duplicate username %", user.getUsername()));
	            throw new RuntimeException("Duplicate username.");
	        }
	        User user1 = new User();
	        user1.setEmail(user.getEmailId());
	        user1.setFirstName(user.getFirstName());
	        user1.setLastName(user.getLastName());
	        user1.setUsername(user.getUsername());
	        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	        String pass=passwordGenerator.generateRandomPassword(8);
	        String encodedPassword = passwordEncoder.encode(pass);
	        System.out.println(pass);
	        
	        user1.setPassword(encodedPassword);
	                                                                                                                                                                                        
//Validation for super admin role
	        User user2=null;
	        List<Authority> role=authorityRepository.findAll();
	        String name=role.get(0).getName();
	        List<String> n=new ArrayList<String>();
	        n.add(name);
	        
	        List<Authority> addAuthorities=authorityRepository.find(user.getRoletype());
	       
	        if(n.equals(user.getRoletype()))
	        {
	        	throw new CustomExceptions("You cannot add this role ");
	        }
	        else
	        {
            user1.setAuthorities(addAuthorities);
           user2= repository.save(user1);
	        }
	        
            user1.setAuthorities(addAuthorities);
	          
				  MailData mail = new MailData();
						 mail.setSubject("Welcome to Student Management System Program");
				  mail.setToEmail(user.getEmailId());
				  mail.setContent("You were added by " +"Username :"+user.getUsername() +"\n"+ "password :"+pass);
				  emailService.sendEmail(mail);
				  return user2;
		
	}
	
	public UserDetailsServiceImpl() {
		super();
	}


	@Override
	@Transactional
	public User update(UserDto user) {
		
Optional<User> userdb=this.repository.findById(user.getId());
		
		if(userdb.isPresent()) {
			User userUpdate=userdb.get();
			userUpdate.setId(user.getId());
			userUpdate.setUsername(user.getUsername());
			userUpdate.setFirstName(user.getFirstName());
			userUpdate.setLastName(user.getLastName());
			userUpdate.setEmail(user.getEmailId());
		    userUpdate.setPassword(new BCryptPasswordEncoder(8).encode(user.getPassword()));
		    repository.save(userUpdate);
		    return userUpdate;
		}
		else {
			throw new CustomExceptions("Record not found with id" + user.getId());
		}
		
	}
	@Override
	@Transactional
	public void delete(int id) {
Optional<User> userdb=this.repository.findById(id);
		
		if(userdb.isPresent()) {
			
			this.repository.deleteById(id);
		}
		else {
			throw new CustomExceptions("Record not found with id  :" +id);
		}
	
	}
	
	@Override
	@Transactional
	public User getUserById(int id) {
		Optional<User> userdb=this.repository.findById(id);
		if(userdb.isPresent()) {
			return userdb.get();
		}
		
		else {
			throw  new CustomExceptions("Record not found with id  :" +id);
		}
		
	}
}
