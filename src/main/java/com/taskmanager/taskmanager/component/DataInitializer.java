package com.taskmanager.taskmanager.component;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.taskmanager.taskmanager.entity.TODO_STATUS;
import com.taskmanager.taskmanager.entity.Todo;
import com.taskmanager.taskmanager.entity.UserEntity;
import com.taskmanager.taskmanager.repository.TodoRepository;
import com.taskmanager.taskmanager.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TodoRepository todoRepository;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ADMIN");
            admin.setFirstname("Admin");
            admin.setLastname("User");
            admin.setEmail("admin@example.com");
            userRepository.save(admin);
            System.out.println("Admin user created with username 'admin' and password 'admin'");
        }

        if (todoRepository.findAll().isEmpty()) {
            // Add some initial todos
            for (int i = 1; i <= 5; i++) {
                Todo todo = new Todo();
                todo.setTitle("Todo " + i);
                todo.setDescription("Description for Todo " + i);
                todo.setStatus(TODO_STATUS.PENDING);
                todo.setDueDate(LocalDateTime.now().plusDays(i));
                todoRepository.save(todo);
            }
            System.out.println("Initial todos created.");
        } 
    }
}

