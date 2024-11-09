package com.dan.taskmanager.repository;

import com.dan.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);

    List<Task> findAllByUserId(Long userId);

    int updateTitle(Long id, String title);
}
