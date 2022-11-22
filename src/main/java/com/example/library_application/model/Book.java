package com.example.library_application.model;

import com.example.library_application.model.enumerations.Genre;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToMany
    private List<Author> authors;

    public Book(String title, Genre genre, List<Author> authors) {
        this.title = title;
        this.genre = genre;
        this.authors = authors;
    }

    public Book() {
    }
}
