/*package com.project.sms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.project.sms.model.User;
import com.project.sms.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
    @Transactional(readOnly = true)
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	@Transactional
	public void create(User user) {
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
	public void delete(Long id) {
	repository.deleteById(id);
	}
	@Override
	public void delete(User user) {
		repository.delete(user);
		
	}


}*/
