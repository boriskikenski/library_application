package com.example.library_application.web.controller;

import com.example.library_application.model.Author;
import com.example.library_application.service.AuthorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @QueryMapping
    Author getAuthor(@Argument String firstName,
                     @Argument String lastName){
        return this.authorService.getAuthor(firstName, lastName);
    }

    @MutationMapping
    Author createAuthor(@Argument String firstName,
                        @Argument String lastName,
                        @Argument LocalDate dateOfBirth){
        this.authorService.createAuthor(firstName, lastName, dateOfBirth);
        return this.authorService.findLastAuthor();
    }
}
