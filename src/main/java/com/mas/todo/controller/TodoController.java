package com.mas.todo.controller;

import com.mas.todo.dto.TodoDto;
import com.mas.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/todo")
@AllArgsConstructor
public class TodoController {

    TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable(name = "id") Integer id) {
        TodoDto todo = todoService.getTodoById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable(name = "id") Integer id, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.updateTodoById(id, todoDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(todoService.deleteTodoById(id), HttpStatus.OK);
    }

    @PatchMapping("/complete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(todoService.completeTodo(id), HttpStatus.OK);
    }

    @PatchMapping("/incomplete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable(name = "id") Integer id) {
        return new ResponseEntity<>(todoService.inCompelteTodo(id), HttpStatus.OK);
    }
}
