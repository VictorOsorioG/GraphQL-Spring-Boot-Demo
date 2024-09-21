package com.globant.udemy.course.graphql_spring_boot.controller;

import com.globant.udemy.course.graphql_spring_boot.model.Book;
import com.globant.udemy.course.graphql_spring_boot.model.dto.BookDto;
import com.globant.udemy.course.graphql_spring_boot.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@GraphQlTest(BookController.class)
class BookControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private BookServiceImpl bookService;

    @Test
    void books() {
        graphQlTester
                .documentName("books")
                .execute()
                .path("book")
                .entityList(Book.class)
                .hasSize(2);
    }

    @Test
    void booksEntity() {

        Mockito.when(bookService.getAllBooks())
                .thenReturn(
                        List.of(
                                BookDto.builder()
                                        .id(1L)
                                        .name("TEST")
                                        .pageCount(1000)
                                        .build()
                        )
                );

        graphQlTester
                .documentName("bookDtos")
                .execute()
                .path("bookDtos")
                .matchesJson("""
                        [
                            {
                                "name": "TEST",
                                "pageCount": 1000
                            }
                        ]
                        """);
    }
}