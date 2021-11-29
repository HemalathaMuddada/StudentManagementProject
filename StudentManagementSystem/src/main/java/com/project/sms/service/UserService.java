package com.project.sms.service;

import java.util.List;

import com.project.sms.model.User;

public interface UserService {
public List<User> getAll();
	
	public void create(User user) throws Exception;
	
	public User update(User user);
	
	 public void delete(int id);
	 
//	 public void delete(User user);

}
