package com.taskmanager.taskmanager.repository;

import org.springframework.stereotype.Repository;

import com.taskmanager.taskmanager.entity.Todo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    
    Optional<Todo> findByTitle(String title);


}
