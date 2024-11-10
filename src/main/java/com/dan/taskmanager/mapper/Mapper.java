package com.dan.taskmanager.mapper;

public interface Mapper<F, T> {
    T map(F from);
}
