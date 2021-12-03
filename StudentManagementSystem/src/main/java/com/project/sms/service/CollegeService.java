package com.project.sms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sms.dto.Orgnaizationdto;
import com.project.sms.model.College;
import com.project.sms.model.Department;
import com.project.sms.model.User;
import com.project.sms.repository.CollegeRepository;
import com.project.sms.repository.DepartmentRepository;
import com.project.sms.repository.UserRepository;

@Service
public class CollegeService {
	@Autowired
	private CollegeRepository collegeRepository;
	   @Autowired
	   private DepartmentRepository departmentRepository;
	   @Autowired
	   private UserRepository repository;
	   @Transactional
	   public String save(Orgnaizationdto orgnaizationdto) {
		   College college=new College();
		   college.setCollege_name(orgnaizationdto.getCollege_name());
		   college.setCollege_code(orgnaizationdto.getCollege_code());
		  Long college_id= collegeRepository.save(college).getId();
		   Department department=new Department();
		   department.setDepartment_name(orgnaizationdto.getDepartment_name());
		   department.setDepartment_code(orgnaizationdto.getDepartment_code());
		   department.setId(college_id);
		   departmentRepository.save(department);
	
		   return "saved";
		   
	   }
	   
	   
}
