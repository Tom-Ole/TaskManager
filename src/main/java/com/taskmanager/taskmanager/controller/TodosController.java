package com.taskmanager.taskmanager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.taskmanager.taskmanager.entity.Todo;
import com.taskmanager.taskmanager.repository.TodoRepository;

import org.springframework.ui.Model;


@Controller
public class TodosController {
    
    private final TodoRepository todoRepository;

    public TodosController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todos")
    public String getTodos(Model model) {
        
        List<Todo> todos = todoRepository.findAll();

        model.addAttribute("todos", todos);

        return "todos"; // This should return the view name for todos
    }
}
