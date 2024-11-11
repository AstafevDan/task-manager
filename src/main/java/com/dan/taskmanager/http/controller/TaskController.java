package com.dan.taskmanager.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {
    @GetMapping("/tasks")
    public String showTasksPage() {
        return "tasks_list";
    }
}
