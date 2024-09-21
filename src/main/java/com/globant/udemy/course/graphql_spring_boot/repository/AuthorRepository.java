package com.globant.udemy.course.graphql_spring_boot.repository;

import com.globant.udemy.course.graphql_spring_boot.model.Author;
import com.globant.udemy.course.graphql_spring_boot.model.Book;

import java.util.List;

public class AuthorRepository {
    static List<Author> authors = List.of(
            new Author(1, "Arthur"),
            new Author(2, "Edgar")
    );

    public static List<Author> getAll() {
        return authors;
    }

    public static Author getById(Integer id) {
        return authors.stream()
                .filter(author -> author.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
