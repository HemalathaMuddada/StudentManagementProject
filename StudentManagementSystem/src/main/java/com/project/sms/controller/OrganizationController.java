package com.project.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.dto.Orgnaizationdto;
import com.project.sms.service.CollegeService;

@RestController
public class OrganizationController {

	@Autowired
	private CollegeService service;
	
	@PostMapping("/org")
	public String save(@RequestBody Orgnaizationdto orgnaizationdto) {
		
		return service.save(orgnaizationdto);
		
	}
}
