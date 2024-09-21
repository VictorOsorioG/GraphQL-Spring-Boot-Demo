package com.globant.udemy.course.graphql_spring_boot.controller;

import com.globant.udemy.course.graphql_spring_boot.model.Author;
import com.globant.udemy.course.graphql_spring_boot.model.Book;
import com.globant.udemy.course.graphql_spring_boot.model.dto.AuthorDto;
import com.globant.udemy.course.graphql_spring_boot.model.dto.BookDto;
import com.globant.udemy.course.graphql_spring_boot.repository.AuthorRepository;
import com.globant.udemy.course.graphql_spring_boot.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final String LOG_PREFIX = "Author GraphQL Controller >>>";
    private final AuthorService authorService;

    @SchemaMapping
    public Author author(Book book) {
        log.info("{} Resolving non entity book author", LOG_PREFIX);
        return AuthorRepository.getById(book.authorId());
    }

    @SchemaMapping
    public AuthorDto author(BookDto bookDto) {
        log.info("{} Resolving book author", LOG_PREFIX);
        return authorService.getAuthorByBookId(bookDto.getId());
    }
}
