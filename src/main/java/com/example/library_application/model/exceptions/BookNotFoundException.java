package com.example.library_application.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String title) {
        super(String.format("Book with title: %s is not found", title));
    }
    public BookNotFoundException(Long bookId) {
        super(String.format("Book with id: %d is not found", bookId));
    }
}
