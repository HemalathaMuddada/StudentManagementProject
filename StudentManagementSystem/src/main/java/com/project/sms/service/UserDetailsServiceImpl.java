package com.project.sms.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sms.email.UserCreatedEvent;
import com.project.sms.model.RoleType;
import com.project.sms.model.User;
import com.project.sms.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService,UserService {
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	private UserRepository repository;

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
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	@Transactional
	public void create(User user) {
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
	        user.setPassword(new BCryptPasswordEncoder(8).encode(user.getPassword()));
	  repository.save(user);
	  publisher.publishEvent(new UserCreatedEvent(user));
		
	}

	public UserDetailsServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	@Transactional
	public User update(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}
	//Deleting Details........
	@Override
	@Transactional
	public void delete(int id) {
	repository.deleteById(id);
	}
	/*@Override
	public void delete(User user) {
		repository.delete(user);
		
	}*/



}
