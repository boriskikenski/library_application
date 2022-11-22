package com.example.library_application.service;

import com.example.library_application.model.Author;
import com.example.library_application.model.Book;
import com.example.library_application.model.enumerations.Genre;

import java.util.List;

public interface BookService {
    List<Book> listAllBooks();
    Book findByTitle(String title);
    void createBook(String title, Genre genre, List<Long> authorsId);
    void updateBook(Long bookId, String title, Genre genre, List<Author> authors);
    void deleteBook(Long bookId);
}
