package com.project.sms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Email {
	 private String mailFrom;
	 
	    private String mailTo;
	 
	    private String mailCc;
	 
	    private String mailBcc;
	 
	    private String mailSubject;
	 
	    private String mailContent;
	 
	    private String contentType;
	 
	    
	 
	    public Email() {
	        contentType = "text/plain";
	    }
}