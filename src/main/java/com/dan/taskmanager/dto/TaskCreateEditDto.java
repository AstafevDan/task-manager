package com.dan.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateEditDto {

    private String title;

    private Boolean completed;

    private Instant createdAt;

    private Long userId;
}
