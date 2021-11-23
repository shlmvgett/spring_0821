package com.ots.springbooks.controllers;

import com.ots.springbooks.models.Author;
import com.ots.springbooks.models.Book;
import com.ots.springbooks.models.Genre;
import com.ots.springbooks.service.AuthorService;
import com.ots.springbooks.service.BookService;
import com.ots.springbooks.service.GenreService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class BookController {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;

  public BookController(
      BookService bookService, AuthorService authorService, GenreService genreService) {
    this.bookService = bookService;
    this.authorService = authorService;
    this.genreService = genreService;
  }

  @GetMapping("/")
  public String listPage(Model model) {
    List<Book> books = bookService.getAllBooks();
    model.addAttribute("books", books);
    return "list";
  }

  @GetMapping("/edit")
  public String editPage(@RequestParam("id") Long id, Model model) {
    if (id != null) {
      Book book = bookService.getBookById(id).orElseThrow(NotFoundException::new);
      model.addAttribute("book", book);
      return "edit";
    } else {
      return "redirect:/";
    }
  }

  @PostMapping("/edit")
  public String editBook(Book book, Model model) {
    Book saved = bookService.updateBook(book);
    model.addAttribute(saved);
    return "redirect:/";
  }

  @GetMapping("/add")
  public String addPage(Model model) {
    List<Author> authors = authorService.getAllAuthors();
    List<Genre> genres = genreService.getAllGenres();
    model.addAttribute("authors", authors);
    model.addAttribute("genres", genres);
    return "save";
  }

  @PostMapping("/add")
  public String addBook(Book book, Model model) {
    Book saved = bookService.insertBook(book);
    model.addAttribute(saved);
    return "redirect:/";
  }
}
