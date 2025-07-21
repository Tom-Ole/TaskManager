package com.taskmanager.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {
    
    @GetMapping("/calendar")
    public String getCalendar(Model model) {
        // Logic to retrieve calendar events
        return "calendar"; // This should return the view name for calendar
    }

}
