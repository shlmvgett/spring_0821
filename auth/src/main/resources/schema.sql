DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id   BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id    BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(255)
);

DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id        BIGSERIAL NOT NULL PRIMARY KEY,
    title     VARCHAR(255),
    owner     VARCHAR(255),
    author_id BIGINT,
    genre_id  BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    text    VARCHAR(255),
    book_id BIGINT,
    FOREIGN KEY (book_id) REFERENCES books (id)
);

DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);
