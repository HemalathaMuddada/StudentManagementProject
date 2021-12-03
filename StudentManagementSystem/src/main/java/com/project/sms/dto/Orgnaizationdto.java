package com.project.sms.dto;

import java.util.List;

import com.project.sms.model.College;
import com.project.sms.model.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orgnaizationdto {
	

	private Long id;
	private String college_name;
	private String college_code;
	private String department_name;
	private String department_code;
	
	// private List<College> college;
	  //  private List<Department> department;
}
