package com.dan.taskmanager.dto;

import lombok.Value;

@Value
public class TaskReadDto {
    Long id;
    String title;
    Boolean completed;
    UserReadDto user;
}
