package com.example.library_application.web.rest;

import com.example.library_application.model.Author;
import com.example.library_application.model.Book;
import com.example.library_application.model.enumerations.Genre;
import com.example.library_application.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return this.bookService.listAllBooks();
    }

    @GetMapping("/by-title")
    public Book getBookByTitle(String title){
        return this.bookService.findByTitle(title);
    }

    @PostMapping("/add-book")
    public void createBook(@RequestParam String title,
                            @RequestParam Genre genre,
                            @RequestParam List<Long> authorsId){
        this.bookService.createBook(title, genre, authorsId);
    }

    @PostMapping("/edit-book")
    public void updateBook(@RequestParam Long bookId,
                            @RequestParam String title,
                            @RequestParam Genre genre,
                            @RequestParam List<Long> authorsId){
        this.bookService.updateBook(bookId, title, genre, authorsId);
    }

    @PostMapping("/delete-book")
    public void deleteBook(@RequestParam Long bookId){
        this.bookService.deleteBook(bookId);
    }
}
