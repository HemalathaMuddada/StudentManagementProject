package com.project.sms.dto;


import java.util.List;

import com.project.sms.model.College;
import com.project.sms.model.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@ToString

public class UserDto {

	private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailId;
    private List<String> roletype;
    
    
   
}