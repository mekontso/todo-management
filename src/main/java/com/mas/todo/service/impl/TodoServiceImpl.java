package com.mas.todo.service.impl;

import com.mas.todo.dto.TodoDto;
import com.mas.todo.entity.Todo;
import com.mas.todo.repository.TodoRepository;
import com.mas.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        // convert todoDto to entity and save it to database
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());

        Todo todo = modelMapper.map(todoDto, Todo.class);

        // save the entity to database
        Todo savedTodo = todoRepository.save(todo);

        // convert the saved entity to dto and return it
//        TodoDto savedTodoDto = new TodoDto();
//        savedTodoDto.setId(savedTodo.getId());
//        savedTodoDto.setTitle(savedTodo.getTitle());
//        savedTodoDto.setDescription(savedTodo.getDescription());
//        savedTodoDto.setCompleted(savedTodo.isCompleted());
        return modelMapper.map(savedTodo, TodoDto.class);
    }
}
