package com.example.library_application.service.impl;

import com.example.library_application.model.Author;
import com.example.library_application.model.exceptions.AuthorNotFoundException;
import com.example.library_application.repositoy.AuthorRepository;
import com.example.library_application.service.AuthorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private static Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void createAuthor(String firstName, String lastName, LocalDate dateOfBirth) {
        this.authorRepository.save(new Author(firstName, lastName, dateOfBirth));
    }

    @Override
    public Author getAuthor(String firstName, String lastName) {
        return this.authorRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new AuthorNotFoundException(firstName, lastName));
    }

    @Override
    public void logTheTopFiveAuthorsNames() {
        List<String> topFiveAuthors = getTopFiveAuthors();
        if(topFiveAuthors.isEmpty()) {
            logger.info("List is empty");
        } else topFiveAuthors.forEach(a -> logger.info("%s", a));
    }

    @Override
    public List<String> getTopFiveAuthors() {
        Comparator<Author> comparator = Comparator.comparing(a -> a.getBooks().stream().count());
        return this.authorRepository.findAll().stream()
                .sorted(comparator.reversed())
                .limit(5)
                .map(author -> author.getFirstName())
                .collect(Collectors.toList());
    }
}
