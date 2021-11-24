package com.project.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.model.User;
import com.project.sms.service.UserDetailsServiceImpl;

@RestController
public class UserController {
	@Autowired
private UserDetailsServiceImpl detailsServiceImpl; 
	
	/*public UserController(UserDetailsServiceImpl detailsServiceImpl) {

		this.detailsServiceImpl = detailsServiceImpl;
	}
	*/

	@GetMapping(value="/user")
    public List<User> getAll() {
        return detailsServiceImpl.getAll();
    }
	
	@RequestMapping(value="/user",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody User user) {
        detailsServiceImpl.create(user);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

	@RequestMapping(value="/user/{id}",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> delete(@RequestBody User user) {
        detailsServiceImpl.delete(user);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
	
	@RequestMapping(value="/user/{id}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody User user) {
        detailsServiceImpl.update(user);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
