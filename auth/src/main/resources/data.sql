INSERT INTO authors (name) values ('author_1');
INSERT INTO authors (name) values ('author_2');

INSERT INTO genres (title) values ('genre_1');
INSERT INTO genres (title) values ('genre_2');
INSERT INTO genres (title) values ('genre_3');

INSERT INTO books (title, owner, author_id, genre_id) values ('book_1', 'admin', 1, 1);
INSERT INTO books (title, owner, author_id, genre_id) values ('book_2', 'user', 1, 1);
INSERT INTO books (title, owner, author_id, genre_id) values ('book_3', 'admin', 2, 2);
INSERT INTO books (title, owner, author_id, genre_id) values ('book_4', 'admin', 2, 3);

INSERT INTO comments (text, book_id) values ('comment_1', 1);
INSERT INTO comments (text, book_id) values ('comment_2', 1);
INSERT INTO comments (text, book_id) values ('comment_3', 1);
INSERT INTO comments (text, book_id) values ('comment_4', 2);
INSERT INTO comments (text, book_id) values ('comment_5', 3);
INSERT INTO comments (text, book_id) values ('comment_6', 4);

-- admin:admin
-- user:admin
INSERT INTO users(login, password, role) values ('admin', '$2a$12$CNpV1WwpfCw8zMyyGegt/uNOE.egqQNEmquWu7piq54lA7tE7XQuu', 'ROLE_ADMIN');
INSERT INTO users(login, password, role) values ('user', '$2a$12$CNpV1WwpfCw8zMyyGegt/uNOE.egqQNEmquWu7piq54lA7tE7XQuu', 'ROLE_USER');

