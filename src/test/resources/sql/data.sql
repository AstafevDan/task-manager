INSERT INTO users(id, username, password_hash, created_at)
VALUES (1, 'ivan@gmail.com', '123', timezone('utc', now())) ,
       (2, 'maria@gmail.com', '123', timezone('utc', now()));
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO tasks(id, title, user_id)
VALUES (1, 'Cook', 1) ,
       (2, 'Learn Java',1) ,
       (3, 'Clean room',1) ,
       (4, 'Help mom', 2) ,
       (5, 'Buy some cheese',2) ,
       (6, 'Walking with dog',2) ,
       (7, 'Do your homework',1);
SELECT SETVAL('tasks_id_seq', (SELECT MAX(id) FROM tasks));