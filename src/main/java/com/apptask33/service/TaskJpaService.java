package com.apptask33.service;

import java.util.ArrayList;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.apptask33.model.Task;
import com.apptask33.model.User;
import com.apptask33.repository.TaskJpaRepository;
import com.apptask33.repository.TaskRepository;
import com.apptask33.repository.UserJpaRepository;

@Service
public class TaskJpaService  implements TaskRepository{

	@Autowired
	private TaskJpaRepository taskJpaRepository;
	@Autowired
	private UserJpaRepository userJpaRepository;
	@Override
	public ArrayList<Task> getTasks() {
		List<Task> taskList=taskJpaRepository.findAll();
		ArrayList<Task> tasks=new ArrayList<>(taskList);
		return tasks;
	}
	
	@Cacheable(cacheNames="tasks", key="#taskId")
	@Override
	public Task getTaskById(int taskId) {
		System.out.println("GET API H2 Database calling.");
		try
		{
			Task task=taskJpaRepository.findById(taskId).get();
			return task;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Task addTask(Task task) {
		 User user=task.getUser(); 
		 //"user": {"id":2}
		 int userId=user.getId(); //2
		 try 
		 {
			 User user1=userJpaRepository.findById(userId).get();
		     task.setUser(user1);
		     taskJpaRepository.save(task);
		     return task;
		 }
		 catch(Exception e)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		 
	}

	@CachePut(cacheNames="tasks", key="#task.taskId")
	@Override
	public Task updateTask(int taskId, Task task) {
		System.out.println("UPDATE API H2 Database calling.");
		try 
		{
		Task existingTask= taskJpaRepository.findById(taskId).get();
		if(task.getTaskName()!=null)
		{
			existingTask.setTaskName(task.getTaskName());
		}
		
		if(task.getUser()!=null)
		{
			User user=task.getUser(); //
			int userId=user.getId(); //2
			User newUser=userJpaRepository.findById(userId).get();
			existingTask.setUser(newUser);
		}
		taskJpaRepository.save(existingTask);
		return existingTask;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@CacheEvict(cacheNames="tasks", key="#taskId")
	@Override
	public void deleteTask(int taskId) {
		System.out.println("DELETE API H2 Database calling.");
		try 
		{
		Task task= taskJpaRepository.findById(taskId).get();
		if(task!=null)
		{
			taskJpaRepository.deleteById(taskId);
		}
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	@Cacheable(cacheNames="users", key="#taskId")
	@Override
	public User getTaskUser(int taskId) {
		System.out.println("GET API H2 Database calling.");
		try 
		{
		Task task=taskJpaRepository.findById(taskId).get();
		return task.getUser();
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
