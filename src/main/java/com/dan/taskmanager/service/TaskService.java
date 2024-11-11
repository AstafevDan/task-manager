package com.dan.taskmanager.service;

import com.dan.taskmanager.dto.TaskCreateEditDto;
import com.dan.taskmanager.dto.TaskReadDto;
import com.dan.taskmanager.mapper.TaskCreateEditMapper;
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
    private final TaskCreateEditMapper taskCreateEditMapper;

    public List<TaskReadDto> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskReadMapper::map)
                .toList();
    }

    public Optional<TaskReadDto> findById(Long id) {
        return taskRepository.findById(id).map(taskReadMapper::map);
    }

    @Transactional
    public TaskReadDto create(TaskCreateEditDto dto) {
        return Optional.of(dto)
                .map(taskCreateEditMapper::map)
                .map(taskRepository::save)
                .map(taskReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<TaskReadDto> update(Long id, TaskCreateEditDto dto) {
        return taskRepository.findById(id)
                .map(entity -> taskCreateEditMapper.map(dto, entity))
                .map(taskRepository::saveAndFlush)
                .map(taskReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return taskRepository.findById(id)
                .map(entity -> {
                    taskRepository.delete(entity);
                    taskRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
