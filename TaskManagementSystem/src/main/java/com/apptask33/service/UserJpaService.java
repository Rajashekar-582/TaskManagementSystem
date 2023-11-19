package com.apptask33.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.apptask33.model.User;
import com.apptask33.repository.UserJpaRepository;
import com.apptask33.repository.UserRepository;

@Service
public class UserJpaService implements UserRepository{

	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Override
	public ArrayList<User> getUsers() {
		List<User> userList=userJpaRepository.findAll();
		ArrayList<User> users=new ArrayList<>(userList);
		return users;
	}

	@Cacheable(cacheNames="users", key="#userId")
	@Override
	public User getUserById(int userId) {
		System.out.println("GET API H2 Database calling.");
		try
		{
		User user= userJpaRepository.findById(userId).get();
		return user;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public User addUser(User user) {
		userJpaRepository.save(user);
		 return user;
	}

	@CachePut(cacheNames="users", key="#user.userId")
	@Override
	public User updateUser(int userId, User user) {
		System.out.println("UPDATE API H2 Database calling.");
		try
		{
		User existingUser= userJpaRepository.findById(userId).get();
		if(user.getUserName()!=null)
		{
			existingUser.setUserName(user.getUserName());
		}
		 userJpaRepository.save(existingUser);
		return existingUser;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@CacheEvict(cacheNames="users", key="#userId")
	@Override
	public void deleteUser(int userId) {
		System.out.println("DELETE API H2 Database calling.");
		try
		{
		User customer= userJpaRepository.findById(userId).get();
		if(customer!=null)
		{
			userJpaRepository.deleteById(userId);
		}
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	
}
