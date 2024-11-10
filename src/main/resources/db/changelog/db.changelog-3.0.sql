--liquibase formatted sql

--changeset dan:1
ALTER TABLE users
ALTER COLUMN created_at DROP NOT NULL;

--changeset dan:2
ALTER TABLE tasks
ALTER COLUMN created_at DROP NOT NULL;

--changeset dan:3
ALTER TABLE tasks
ALTER COLUMN completed SET DEFAULT false;