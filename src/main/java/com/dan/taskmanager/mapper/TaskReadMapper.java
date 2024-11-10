package com.dan.taskmanager.mapper;

import com.dan.taskmanager.dto.TaskReadDto;
import com.dan.taskmanager.dto.UserReadDto;
import com.dan.taskmanager.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskReadMapper implements Mapper<Task, TaskReadDto> {

    private final UserReadMapper userReadMapper;

    @Override
    public TaskReadDto map(Task from) {
        UserReadDto user = Optional.ofNullable(from.getUser())
                .map(userReadMapper::map)
                .orElse(null);

        return new TaskReadDto(
                from.getId(),
                from.getTitle(),
                from.getCompleted(),
                user
        );
    }
}
