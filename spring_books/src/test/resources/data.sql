INSERT INTO authors (id, name) values (1, 'author_1');
INSERT INTO authors (id, name) values (2, 'author_2');

INSERT INTO genres (id, title) values (1, 'genre_1');
INSERT INTO genres (id, title) values (2, 'genre_2');
INSERT INTO genres (id, title) values (3, 'genre_3');

INSERT INTO books (title, author_id, genre_id) values ('book_1', 1, 1);
INSERT INTO books (title, author_id, genre_id) values ('book_2', 1, 1);
INSERT INTO books (title, author_id, genre_id) values ('book_3', 2, 2);
INSERT INTO books (title, author_id, genre_id) values ('book_4', 2, 3);