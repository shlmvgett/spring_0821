DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id   BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id    BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255)
);

DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id        BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title     VARCHAR(255),
    author_id BIGINT,
    genre_id  BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
    id      BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    text    VARCHAR(255),
    book_id BIGINT,
    FOREIGN KEY (book_id) REFERENCES books (id)
);