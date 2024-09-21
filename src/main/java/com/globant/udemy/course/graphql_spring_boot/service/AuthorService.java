package com.globant.udemy.course.graphql_spring_boot.service;

import com.globant.udemy.course.graphql_spring_boot.model.AuthorEntity;
import com.globant.udemy.course.graphql_spring_boot.model.dto.AuthorDto;
import com.globant.udemy.course.graphql_spring_boot.model.dto.BookDto;

import java.util.List;

public interface AuthorService {

    AuthorDto getAuthorByBookId(Long id);

    AuthorEntity getReferenceById(Long authorId);
}
