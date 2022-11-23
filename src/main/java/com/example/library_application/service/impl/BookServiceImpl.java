package com.example.library_application.service.impl;

import com.example.library_application.model.Author;
import com.example.library_application.model.Book;
import com.example.library_application.model.enumerations.Genre;
import com.example.library_application.model.exceptions.AuthorNotFoundException;
import com.example.library_application.model.exceptions.BookNotFoundException;
import com.example.library_application.repositoy.AuthorRepository;
import com.example.library_application.repositoy.BookRepository;
import com.example.library_application.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findByTitle(String title) {
        return this.bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException(title));
    }

    @Override
    public void createBook(String title, Genre genre, List<Long> authorsId) {
        List<Author> authors = getAuthorsFromId(authorsId);
        this.bookRepository.save(new Book(title, genre, authors));
    }

    @Override
    public void updateBook(Long bookId, String title, Genre genre, List<Long> authorsId) {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        List<Author> authors = getAuthorsFromId(authorsId);

        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthors(authors);

        this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }

    @Override
    public Book findLastBook() {
        Comparator<Book> comparator = Comparator.comparing(b -> b.getBookId());
        return this.bookRepository.findAll().stream()
                .sorted(comparator.reversed())
                .limit(1)
                .findFirst().get();
    }

    @Override
    public List<Author> getAuthorsFromId(List<Long> authorsId) {
        return authorsId.stream()
                .map(authorId -> this.authorRepository.findById(authorId)
                        .orElseThrow(() -> new AuthorNotFoundException(authorId)))
                .collect(Collectors.toList());
    }

    @Override
    public Book findById(Long bookId) {
        return this.bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }
}
