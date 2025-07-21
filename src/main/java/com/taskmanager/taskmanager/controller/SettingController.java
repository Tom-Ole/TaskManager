package com.taskmanager.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {
 
    @GetMapping("/settings")
    public String getSettings(Model model) {
        // Logic to retrieve settings
        return "settings"; // This should return the view name for settings
    }
    
}