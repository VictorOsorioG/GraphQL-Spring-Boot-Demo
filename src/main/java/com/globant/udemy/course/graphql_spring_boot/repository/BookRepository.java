package com.globant.udemy.course.graphql_spring_boot.repository;

import com.globant.udemy.course.graphql_spring_boot.model.Book;

import java.util.List;

public class BookRepository {
    static List<Book> books = List.of(
            new Book(1, "Sherlock", 500,1),
            new Book(2, "Edgar Allan Poe", 300, 2)
    );

    public static List<Book> getAll() {
        return books;
    }

    public static Book getById(Integer id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
