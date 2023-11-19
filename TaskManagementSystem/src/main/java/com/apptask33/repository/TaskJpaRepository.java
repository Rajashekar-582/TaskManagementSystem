package com.apptask33.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptask33.model.Task;

public interface TaskJpaRepository extends JpaRepository<Task,Integer> {

}
