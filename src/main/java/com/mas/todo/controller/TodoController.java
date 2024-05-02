package com.mas.todo.controller;

import com.mas.todo.dto.TodoDto;
import com.mas.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/todo")
@AllArgsConstructor
public class TodoController {

    TodoService todoService;

    // addTodo method will be implemented here
    @PostMapping("/add")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetExchange("/get/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable(name = "id") Integer id) {
        TodoDto todo = todoService.getTodoById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> updateTodo (@PathVariable(name = "id") Integer id, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.updateTodoById(id, todoDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(todoService.deleteTodoById(id),HttpStatus.OK);
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(todoService.completeTodo(id), HttpStatus.OK);
    }

    @PatchMapping("/incomplete/{id}")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(todoService.inCompelteTodo(id), HttpStatus.OK);
    }
}
