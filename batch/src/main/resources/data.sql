INSERT INTO authors (name) values ('author_1');
INSERT INTO authors (name) values ('author_2');

INSERT INTO genres (title) values ('genre_1');
INSERT INTO genres (title) values ('genre_2');
INSERT INTO genres (title) values ('genre_3');

INSERT INTO books (title, author_id, genre_id) values ('book_1', 1, 1);
INSERT INTO books (title, author_id, genre_id) values ('book_2', 1, 1);
INSERT INTO books (title, author_id, genre_id) values ('book_3', 2, 2);
INSERT INTO books (title, author_id, genre_id) values ('book_4', 2, 3);

INSERT INTO comments (text, book_id) values ('comment_1', 1);
INSERT INTO comments (text, book_id) values ('comment_2', 1);
INSERT INTO comments (text, book_id) values ('comment_3', 1);
INSERT INTO comments (text, book_id) values ('comment_4', 2);
INSERT INTO comments (text, book_id) values ('comment_5', 3);
INSERT INTO comments (text, book_id) values ('comment_6', 4);