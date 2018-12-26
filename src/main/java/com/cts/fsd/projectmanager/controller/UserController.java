package com.cts.fsd.projectmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fsd.projectmanager.bean.User;
import com.cts.fsd.projectmanager.service.UserManagerService;


@RestController
public class UserController {

@Autowired
UserManagerService userManagerService;

	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/getUsers", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
    	
    	List<User> users = userManagerService.getAllUsers();
        return users;
    }

	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/addUser", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User userReq) {
    	

		User user = userManagerService.addUser(userReq);
		
    	return user;
    }

	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/updateUser", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User userReq) {
    	

		User user = userManagerService.updateUser(userReq);
		
    	return user;
    }

	@CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value="/deleteUser", produces=MediaType.APPLICATION_JSON_VALUE)
    public long deleteUser( @RequestParam(value="id") String id) {
    	

		long deleteCount = userManagerService.deleteUser(id);
		
    	return deleteCount;
    }}
