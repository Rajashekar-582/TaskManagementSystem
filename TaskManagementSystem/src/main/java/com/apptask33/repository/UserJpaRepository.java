package com.apptask33.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptask33.model.User;

public interface UserJpaRepository extends JpaRepository<User,Integer>{

}
