package com.dan.taskmanager.integration.repository;

import com.dan.taskmanager.entity.Task;
import com.dan.taskmanager.entity.User;
import com.dan.taskmanager.integration.IntegrationTestBase;
import com.dan.taskmanager.repository.TaskRepository;
import com.dan.taskmanager.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class TaskRepositoryIT extends IntegrationTestBase {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final EntityManager em;
    private static final Long TASK_ID = 1L;
    private static final Long TASK_ID_CREATE = 8L;
    private static final String TASK_TITLE = "Task Title";

    @Test
    void checkFindAll() {
        List<Task> tasks = taskRepository.findAll();
        assertNotNull(tasks);
        assertThat(tasks).hasSize(7);
    }

    @Test
    void checkFindById() {
        Optional<Task> task = taskRepository.findById(TASK_ID);
        assertTrue(task.isPresent());
        assertEquals(TASK_ID, task.get().getId());
    }

    @Test
    void checkCreate() {
        User user = new User(3L, "test1@gmail.com", "password");
        Task task = new Task(
            TASK_ID_CREATE,
                "test1",
                false,
                user
        );

        userRepository.saveAndFlush(user);
        Task createdTask = taskRepository.saveAndFlush(task);
        assertNotNull(createdTask);
        assertEquals(TASK_ID_CREATE, createdTask.getId());
    }

    @Test
    void checkUpdateCompleted() {
        int updatedTasks = taskRepository.updateCompleted(TASK_ID, true);
        assertEquals(1, updatedTasks);
    }

    @Test
    void checkUpdateTitle() {
        int updatedTasks = taskRepository.updateTitle(TASK_TITLE, 1L, 3L, 4L);
        assertEquals(3, updatedTasks);
    }

    @Test
    void checkDelete() {
        var maybeTask = taskRepository.findById(TASK_ID);
        assertTrue(maybeTask.isPresent());
        maybeTask.ifPresent(taskRepository::delete);
        em.flush();
        assertTrue(taskRepository.findById(TASK_ID).isEmpty());
    }
}
