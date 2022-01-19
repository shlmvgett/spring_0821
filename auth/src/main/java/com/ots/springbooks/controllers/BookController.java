package com.ots.springbooks.controllers;

import com.ots.springbooks.models.Book;
import com.ots.springbooks.service.BookService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@AllArgsConstructor
public class BookController {

  private final BookService bookService;

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
}
