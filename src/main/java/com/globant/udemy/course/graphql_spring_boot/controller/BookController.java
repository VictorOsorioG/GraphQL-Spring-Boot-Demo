package com.globant.udemy.course.graphql_spring_boot.controller;

import com.globant.udemy.course.graphql_spring_boot.model.Book;
import com.globant.udemy.course.graphql_spring_boot.model.dto.BookDto;
import com.globant.udemy.course.graphql_spring_boot.model.dto.BookRequest;
import com.globant.udemy.course.graphql_spring_boot.repository.BookRepository;
import com.globant.udemy.course.graphql_spring_boot.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

    private final String LOG_PREFIX = "Book GraphQL Controller >>>";
    private final BookService bookService;

    @QueryMapping
    public List<Book> books() {
        log.info("{} Query non entity books", LOG_PREFIX);
        return BookRepository.getAll();
    }

    @QueryMapping
    public Book bookById(@Argument Integer id) {
        log.info("{} Query non entity book by id", LOG_PREFIX);
        return BookRepository.getById(id);
    }

    @QueryMapping
    public List<BookDto> bookDtos() {
        log.info("{} Query books", LOG_PREFIX);
        return bookService.getAllBooks();
    }

    @SchemaMapping
    public String descriptionResolver(BookDto bookDto) {
        log.info("{} Resolving book description", LOG_PREFIX);
        return bookService.resolveDescription(bookDto);
    }

    @MutationMapping
    public Boolean createBook(@Argument BookRequest bookRequest) {
        log.info("{} Mutation create book", LOG_PREFIX);
        bookService.createBook(bookRequest);
        return true;
    }
}
