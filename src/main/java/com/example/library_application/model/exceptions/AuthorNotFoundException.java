package com.example.library_application.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String firstName, String lastName) {
        super(String.format("Author: %s %s is not found", firstName, lastName));
    }
    public AuthorNotFoundException(Long authorId) {
        super(String.format("Author with id: %d is not found", authorId));
    }
}
