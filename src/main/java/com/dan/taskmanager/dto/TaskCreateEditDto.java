package com.dan.taskmanager.dto;

import lombok.Value;

import java.time.Instant;

@Value
public class TaskCreateEditDto {

    String title;

    Boolean completed;

    Instant createdAt;

    Long userId;
}
