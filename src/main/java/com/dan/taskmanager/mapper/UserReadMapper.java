package com.dan.taskmanager.mapper;

import com.dan.taskmanager.dto.UserReadDto;
import com.dan.taskmanager.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User from) {
        return new UserReadDto(
                from.getId(),
                from.getUsername()
        );
    }
}
