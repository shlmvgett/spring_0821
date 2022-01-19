package com.ots.springbooks.controllers;

import com.ots.springbooks.controllers.dto.BookCreationDto;
import com.ots.springbooks.models.Book;
import com.ots.springbooks.service.BookService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@AllArgsConstructor
public class BookRestController {

  private final BookService bookService;

  @GetMapping("/api/books")
  public List<Book> getBooks() {
    return bookService.getAllBooks();
  }

  @PostMapping("/api/book")
  public Book saveClient(@RequestBody BookCreationDto bookCreationDto) {
    return bookService.insertBook(new Book(bookCreationDto));
  }
}
