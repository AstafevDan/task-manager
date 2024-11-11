package com.dan.taskmanager.dto;

import lombok.Value;

import java.time.Instant;

@Value
public class TaskReadDto {
    Long id;
    String title;
    Boolean completed;
    Instant createdAt;
    UserReadDto user;
}
