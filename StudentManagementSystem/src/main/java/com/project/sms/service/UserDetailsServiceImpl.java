package com.project.sms.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sms.model.User;
import com.project.sms.repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService,UserService {

	@Autowired
	private UserRepository repository;

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
		 String hashPassword(String plainTextPassword){
		 BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
		}
	  repository.save(user);
		
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
