package com.dan.taskmanager.integration.service;

import com.dan.taskmanager.dto.TaskCreateEditDto;
import com.dan.taskmanager.dto.TaskReadDto;
import com.dan.taskmanager.integration.IntegrationTestBase;
import com.dan.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class TaskServiceIT extends IntegrationTestBase {
    private final TaskService taskService;

    private static final Long TASK_ID = 1L;
    private static final Long USER_ID = 1L;

    @Test
    void checkFindAll() {
        List<TaskReadDto> tasks = taskService.findAll();
        assertThat(tasks).hasSize(7);
    }

    @Test
    void checkFindById() {
        Optional<TaskReadDto> maybeTask = taskService.findById(TASK_ID);
        assertThat(maybeTask).isPresent();
        maybeTask.ifPresent(taskReadDto -> assertThat(taskReadDto.getTitle()).isEqualTo("Cook"));
    }

    @Test
    void checkCreate() {
        TaskCreateEditDto taskCreateEditDto = new TaskCreateEditDto(
                "Test exercise",
                false,
                Instant.now(),
                USER_ID
        );

        TaskReadDto actualResult = taskService.create(taskCreateEditDto);

        assertEquals(taskCreateEditDto.getTitle(), actualResult.getTitle());
        assertEquals(taskCreateEditDto.getCompleted(), actualResult.getCompleted());
        assertEquals(taskCreateEditDto.getCreatedAt(), actualResult.getCreatedAt());
        assertEquals(taskCreateEditDto.getUserId(), actualResult.getUser().getId());
    }

    @Test
    void checkUpdate() {
        TaskCreateEditDto taskCreateEditDto = new TaskCreateEditDto(
                "Test exercise",
                true,
                Instant.now(),
                USER_ID
        );

        Optional<TaskReadDto> actualResult = taskService.update(TASK_ID, taskCreateEditDto);
        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(task -> {
            assertEquals(taskCreateEditDto.getTitle(), task.getTitle());
            assertEquals(taskCreateEditDto.getCompleted(), task.getCompleted());
            assertEquals(taskCreateEditDto.getCreatedAt(), task.getCreatedAt());
            assertEquals(taskCreateEditDto.getUserId(), task.getUser().getId());
        });
    }

    @Test
    void checkDelete() {
        assertTrue(taskService.delete(TASK_ID));
        assertFalse(taskService.delete(-100L));
    }
}
