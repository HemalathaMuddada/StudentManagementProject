package com.project.sms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter
@Setter
public class StudentData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "username")
    private String username;
	
	@Column(name = "first_name")
    private String first_name;
	
	@Column(name = "last_name")
    private String last_name;
	
	@Column(name = "email")
    private String email;

    @Column(name = "attendance_in_percentage")
    private Long attendance;
    
    @Column(name = "parent_email_id")
    private String parent_email_id;

}
