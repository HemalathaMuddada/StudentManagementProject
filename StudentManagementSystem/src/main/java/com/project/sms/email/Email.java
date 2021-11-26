package com.project.sms.email;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Email {


	 private String mailFrom;
	 
	    private String mailTo;
	 
	    private String mailCc;
	 
	    private String mailBcc;
	 
	    private String mailSubject;
	 
	    private String mailContent;
	 
	    private String contentType;

	    private String template;
	    Map<String, Object> model;
	    
	 
	    public Email() {
	        contentType = "text/plain";
	    }
}