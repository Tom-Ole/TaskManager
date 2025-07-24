package com.taskmanager.taskmanager.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/todos/edit/{id}")
    public String getEditTodoForm(@PathVariable Long id, Model model) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));
        model.addAttribute("todo", todo);

        String dueDateInputString = todo.getDueDate()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")).toString();
        model.addAttribute("dueDateInputString", dueDateInputString);

        return "todos/edit";
    }

    @PostMapping("/todos/edit/{id}")
    public String postEditTodo(@PathVariable Long id, @ModelAttribute Todo todo) {
        todo.setId(id);
        todoRepository.save(todo);
        return "redirect:/todos";
    }

    @GetMapping("/todos/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo Id:" + id));
        todoRepository.delete(todo);
        return "redirect:/todos";
    }

}
