package com.ots.springbooks.controllers;

import com.ots.springbooks.controllers.dto.BookCreationReq;
import com.ots.springbooks.models.Book;
import com.ots.springbooks.service.BookService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class RestBookController {

  private final BookService bookService;

  public RestBookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/api/books")
  public List<Book> getBooks() {
    return bookService.getAllBooks();
  }

  @PostMapping("/api/book")
  public Book saveClient(@RequestBody BookCreationReq bookCreationReq) {
    return bookService.insertBook(new Book(bookCreationReq));
  }
}
