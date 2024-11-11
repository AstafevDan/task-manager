package com.dan.taskmanager.mapper;

import com.dan.taskmanager.dto.TaskCreateEditDto;
import com.dan.taskmanager.entity.Task;
import com.dan.taskmanager.entity.User;
import com.dan.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskCreateEditMapper implements Mapper<TaskCreateEditDto, Task> {

    private final UserRepository userRepository;

    @Override
    public Task map(TaskCreateEditDto from) {
        Task task = new Task();
        copy(from, task);
        return task;
    }

    @Override
    public Task map(TaskCreateEditDto from, Task to) {
        copy(from, to);
        return Mapper.super.map(from, to);
    }

    private void copy(TaskCreateEditDto from, Task task) {
        task.setTitle(from.getTitle());
        task.setCompleted(from.getCompleted());
        task.setCreatedAt(from.getCreatedAt());
        task.setUser(getUser(from.getUserId()));
    }

    private User getUser(Long userId) {
        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }
}
