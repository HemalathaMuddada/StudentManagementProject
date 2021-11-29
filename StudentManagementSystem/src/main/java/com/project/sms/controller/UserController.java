package com.project.sms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping(value="/user")
    public List<User> getAll() {
        return detailsServiceImpl.getAll();
    }
	
	@RequestMapping(value="/user",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@Valid @RequestBody User user) throws Exception{
        detailsServiceImpl.create(user);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.FOUND).ok().build();
    }


	@PutMapping("/edit/{id}")
	private User update(@RequestBody User users,@PathVariable int id) {
		users.setId(id);
		detailsServiceImpl.update(users);
			return users;
		}
		
		@DeleteMapping("delete/{id}")
		private void deleteusers(@PathVariable ("id") int id) {
			detailsServiceImpl.delete(id);
		}

}
