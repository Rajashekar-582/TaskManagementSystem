package com.apptask33.repository;

import java.util.ArrayList;

import com.apptask33.model.User;

public interface UserRepository {
	ArrayList <User> getUsers();
	User getUserById(int userId);
	User addUser(User user);
	User updateUser(int userId,User user);
	void deleteUser(int userId);

}
