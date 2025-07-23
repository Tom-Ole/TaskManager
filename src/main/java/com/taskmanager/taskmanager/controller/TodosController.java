package com.taskmanager.taskmanager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.taskmanager.taskmanager.entity.Todo;
import com.taskmanager.taskmanager.repository.TodoRepository;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




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

    @GetMapping("/todos/create")
    public String getCreateTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todos/create";
    }

    @PostMapping("/todos/create")
    public String postCreateTodo(@ModelAttribute Todo todo) {
        todoRepository.save(todo);
        return "redirect:/todos";
    }
    
    
}
