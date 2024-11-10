package com.dan.taskmanager.service;

import com.dan.taskmanager.dto.TaskReadDto;
import com.dan.taskmanager.mapper.TaskReadMapper;
import com.dan.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskReadMapper taskReadMapper;

    public List<TaskReadDto> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskReadMapper::map)
                .toList();
    }

    public Optional<TaskReadDto> findById(Long id) {
        return taskRepository.findById(id).map(taskReadMapper::map);
    }
}
