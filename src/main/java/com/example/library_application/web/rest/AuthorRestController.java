package com.example.library_application.web.rest;

import com.example.library_application.model.Author;
import com.example.library_application.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("by-name")
    public Author getAuthor(@RequestParam String firstName,
                             @RequestParam String lastName){
        return this.authorService.getAuthor(firstName, lastName);
    }

    @PostMapping("add-author")
    public void createAuthor(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam LocalDate dateOfBirth){
        this.authorService.createAuthor(firstName, lastName, dateOfBirth);
    }
}
