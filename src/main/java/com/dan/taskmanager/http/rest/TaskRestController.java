package com.dan.taskmanager.http.rest;

import com.dan.taskmanager.dto.TaskCreateEditDto;
import com.dan.taskmanager.dto.TaskReadDto;
import com.dan.taskmanager.entity.User;
import com.dan.taskmanager.service.TaskService;
import com.dan.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;
    private final UserService userService;

    @GetMapping
    public List<TaskReadDto> findAll(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return taskService.findAllByUserId(user.getId());
    }

    @GetMapping("/{id}")
    public TaskReadDto findById(@PathVariable Long id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return taskService.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskReadDto create(@RequestBody TaskCreateEditDto taskDto, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        taskDto.setUserId(user.getId());
        return taskService.create(taskDto);
    }

    @PutMapping("/{id}")
    public TaskReadDto update(@PathVariable Long id, @RequestBody TaskCreateEditDto taskDto, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return taskService.update(id, taskDto, user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/title")
    public TaskReadDto updateTitle(@PathVariable Long id, @RequestBody Map<String, String> title, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return taskService.updateTitle(id, title.get("title"), user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/completed")
    public TaskReadDto updateStatus(@PathVariable Long id, @RequestBody Map<String, Boolean> completed, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return taskService.updateStatus(id, completed.get("completed"), user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (!taskService.delete(id, user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
