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

import com.apptask33.model.Task;
import com.apptask33.model.User;
import com.apptask33.service.TaskJpaService;

@RestController
public class TaskController {

	@Autowired
	private TaskJpaService taskJpaService;
	
	@GetMapping("/tasks")
	public ArrayList <Task> getTasks()
	{
		return taskJpaService.getTasks();
	}
	@GetMapping("/tasks/{taskId}")
	public Task getTaskById(@PathVariable("taskId") int taskId)
	{
		return taskJpaService.getTaskById(taskId);
		
	}
	@PostMapping("/tasks/addnewtask")
	public Task addTask(@RequestBody Task task)
	{
		return taskJpaService.addTask(task);
		
	}
	@PutMapping("/tasks/{taskId}")
	public Task updateTask(@PathVariable("taskId") int taskId,@RequestBody Task task)
	{
		return taskJpaService.updateTask(taskId, task);
	}
	@DeleteMapping("/tasks/{taskId}")
	public void deleteTask(@PathVariable("taskId") int taskId)
	{
		taskJpaService.deleteTask(taskId);
	}
	@GetMapping("tasks/{taskId}/user")
	public User getTaskUser(@PathVariable("taskId") int taskId)
	{
		return taskJpaService.getTaskUser(taskId);
	}
	
}
