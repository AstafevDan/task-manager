package com.dan.taskmanager.repository;

import com.dan.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);

    Optional<Task> findByIdAndUserId(Long id, Long userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Task t set t.title = :title where t.id in (:ids)")
    int updateTitle(String title, Long... ids);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Task t set t.completed = :completed where t.id = :id")
    int updateCompleted(Long id, boolean completed);
}
