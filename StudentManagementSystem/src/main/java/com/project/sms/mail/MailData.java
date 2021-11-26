package com.project.sms.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
public class MailData {
 private   String toEmail;
 private   String subject;
 private    String content;
    //String template;
    Map<String, Object> model;
   // Map<String, String> images;
}
