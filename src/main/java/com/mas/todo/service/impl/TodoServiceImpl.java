package com.mas.todo.service.impl;

import com.mas.todo.dto.TodoDto;
import com.mas.todo.entity.Todo;
import com.mas.todo.exception.ResourceNotFoundException;
import com.mas.todo.repository.TodoRepository;
import com.mas.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public TodoDto getTodoById(Integer id) {
        // get the entity from database
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .toList();
    }

    @Override
    public TodoDto updateTodoById(Integer id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id  : " + id));
        todo.setDescription(todoDto.getDescription());
        todo.setTitle(todoDto.getTitle());
        todo.setCompleted(todoDto.isCompleted());

        todo = todoRepository.save(todo);
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public String deleteTodoById(Integer id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id  : " + id));
        todoRepository.delete(todo);
        return "Success delete todo id : "  + id;
    }

    @Override
    public TodoDto completeTodo(Integer id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id  : " + id));
        todo.setCompleted(Boolean.TRUE);
        return modelMapper.map(todoRepository.save(todo), TodoDto.class);
    }

    @Override
    public TodoDto inCompelteTodo(Integer id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id  : " + id));
        todo.setCompleted(Boolean.FALSE);
        return modelMapper.map(todoRepository.save(todo), TodoDto.class);
    }
}
