package com.example.library_application.service;

import com.example.library_application.model.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    void createAuthor(String firstName, String lastName, LocalDate dateOfBirth);
    Author getAuthor(String firstName, String lastName);
    void logTheTopFiveAuthorsNames();
    List<String> getTopFiveAuthors();
}
