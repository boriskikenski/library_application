package com.example.library_application.web.controller;

import com.example.library_application.model.Book;
import com.example.library_application.model.enumerations.Genre;
import com.example.library_application.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    Iterable<Book> getAllBooks(){
        return this.bookService.listAllBooks();
    }

    @QueryMapping
    Book getBookByTitle(@Argument String title){
        return this.bookService.findByTitle(title);
    }

    @MutationMapping
    Book createBook(@Argument String title,
                    @Argument Genre genre,
                    @Argument List<Long> authorsId){
        this.bookService.createBook(title, genre, authorsId);
        return this.bookService.findLastBook();
    }

    @MutationMapping
    Book updateBook(@Argument Long bookId,
                    @Argument String title,
                    @Argument Genre genre,
                    @Argument List<Long> authorsId){
        this.bookService.updateBook(bookId, title, genre, authorsId);
        return this.bookService.findById(bookId);
    }

    @MutationMapping
    Boolean deleteBook(@Argument Long bookId){
        this.bookService.deleteBook(bookId);
        return true;
    }
}
