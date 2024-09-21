package com.globant.udemy.course.graphql_spring_boot.service;

import com.globant.udemy.course.graphql_spring_boot.model.dto.BookDto;
import com.globant.udemy.course.graphql_spring_boot.model.dto.BookRequest;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    void createBook(BookRequest bookRequest);

    String resolveDescription(BookDto bookDto);
}
