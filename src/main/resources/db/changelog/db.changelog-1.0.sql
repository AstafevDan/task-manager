--liquibase formatted sql

--changeset dan:1
CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY ,
    username VARCHAR(64) NOT NULL UNIQUE ,
    password_hash VARCHAR(256) NOT NULL ,
    created_at TIMESTAMP NOT NULL ,
    last_modified_at TIMESTAMP
)