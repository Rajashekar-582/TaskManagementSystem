package com.apptask33.repository;
import java.util.*;

import com.apptask33.model.Task;
import com.apptask33.model.User;

public interface TaskRepository {

	ArrayList <Task> getTasks();
	Task getTaskById(int taskId);
	Task addTask(Task task);
	Task updateTask(int taskId,Task task);
	void deleteTask(int taskId);
	User getTaskUser(int taskId);
	
}
