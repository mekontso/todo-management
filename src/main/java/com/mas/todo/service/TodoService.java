package com.mas.todo.service;

import com.mas.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodoById(Integer id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodoById(Integer id, TodoDto todoDto);

    String deleteTodoById(Integer id);

    TodoDto completeTodo(Integer id);

    TodoDto inCompelteTodo(Integer id);

}
