package com.project.sms.dto;




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

	
	private String username ;
	
	
}
