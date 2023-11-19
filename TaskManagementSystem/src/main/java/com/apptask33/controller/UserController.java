package com.apptask33.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apptask33.model.User;
import com.apptask33.service.UserJpaService;

@RestController
public class UserController {
	@Autowired
	private UserJpaService userJpaService;
	
	@GetMapping("/user")
	public ArrayList <User> getUsers()
	{
		return userJpaService.getUsers();
	}
	@GetMapping("/user/{userId}")
	public User getUserById(@PathVariable int userId)
	{
		return userJpaService.getUserById(userId);
		
	}
	@PostMapping("/user/addnewuser")
	public User addUser(@RequestBody User user) {
		return userJpaService.addUser(user);
	}
	@PutMapping("/user/{userId}")
	public User updateUser(@PathVariable int userId,@RequestBody User user)
	{
		return userJpaService.updateUser(userId, user);
	}
	@DeleteMapping("/user/{userId}")
	public void deleteUser(@PathVariable int userId)
	{
		userJpaService.deleteUser(userId);
	}

}
