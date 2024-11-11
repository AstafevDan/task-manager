package com.dan.taskmanager.http.rest;

import com.dan.taskmanager.dto.TaskCreateEditDto;
import com.dan.taskmanager.dto.TaskReadDto;
import com.dan.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskReadDto> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskReadDto findById(@PathVariable Long id) {
        return taskService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskReadDto create(@RequestBody TaskCreateEditDto taskDto) {
        return taskService.create(taskDto);
    }

    @PutMapping("/{id}")
    public TaskReadDto update(@PathVariable Long id, @RequestBody TaskCreateEditDto taskDto) {
        return taskService.update(id, taskDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!taskService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
