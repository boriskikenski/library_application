package com.example.library_application.jobs;

import com.example.library_application.service.AuthorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final AuthorService authorService;

    public ScheduledTasks(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Scheduled(cron = "0 9 * * *")
    public void logTheTopFiveAuthors() {
        this.authorService.logTheTopFiveAuthorsNames();
    }
}
