--liquibase formatted sql

--changeset dan:1
CREATE TABLE IF NOT EXISTS tasks
(
    id BIGSERIAL PRIMARY KEY ,
    title VARCHAR(32) NOT NULL ,
    completed BOOLEAN NOT NULL ,
    created_at TIMESTAMP NOT NULL ,
    last_modified_at TIMESTAMP ,
    user_id BIGINT REFERENCES users (id) ON DELETE CASCADE
)