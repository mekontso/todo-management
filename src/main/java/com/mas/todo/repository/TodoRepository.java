package com.mas.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mas.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
