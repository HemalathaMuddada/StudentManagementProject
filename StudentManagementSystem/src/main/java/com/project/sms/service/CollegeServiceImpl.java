package com.project.sms.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sms.dto.DepartmentDto;
import com.project.sms.dto.Orgnaizationdto;
import com.project.sms.exceptions.CustomExceptions;
import com.project.sms.model.College;
import com.project.sms.model.Department;
import com.project.sms.model.User;
import com.project.sms.repository.CollegeRepository;
import com.project.sms.repository.DepartmentRepository;
import com.project.sms.repository.UserRepository;

@Service
public class CollegeServiceImpl implements CollegeService {
	@Autowired
	private CollegeRepository collegeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	   @Autowired
	   private UserRepository repository;
	
	  
	
	@Override
	@Transactional
	public College create(Orgnaizationdto orgnaizationdto) throws Exception {

	        College c=new College();
			c.setName(orgnaizationdto.getCollege_name());
			c.setCode(orgnaizationdto.getCollege_code());
			
			List<Department> d=departmentRepository.findByName(orgnaizationdto.getName());
        	c.setDepartment(d);
			
			User user1=this.repository.findByUsername(orgnaizationdto.getUsername());
			c.setUser(user1);
			
		
  
		return collegeRepository.save(c);
	}
	
	@Override
	@Transactional
	public College update(Orgnaizationdto orgnaizationdto) {
		{
			
	        Optional<College> collegedb=this.collegeRepository.findById(orgnaizationdto.getId());
		

			
			if(collegedb.isPresent()) {
				College c=collegedb.get();
				c.setName(orgnaizationdto.getCollege_name());
				c.setCode(orgnaizationdto.getCollege_code());
				
		
				 repository.save(c);
		          return c;
			}
			else {
				throw new CustomExceptions("Record not found with id" + orgnaizationdto.getId());
			}
	}}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Optional<College> college= this.collegeRepository.findById(id);
        if(college.isPresent()) {
			
        	this.collegeRepository.deleteById(id);
		}
		else {
			throw new CustomExceptions("Record not found with id  :" +id);
		}
		
	}

	@Override
	public College findCollegeById(Long id) {

		Optional<College> collegedb=this.collegeRepository.findById(id);
		if(collegedb.isPresent()) {
			return collegedb.get();
		}
		
		else {
			throw  new CustomExceptions("Record not found with id  :" +id);
		}
	}
	

	@Override
		@Transactional
		public List<College> findColleges() {
			
			return this.collegeRepository.findAll();
		}

		}
	 
	   

