package com.globant.udemy.course.graphql_spring_boot.service;

import com.globant.udemy.course.graphql_spring_boot.model.BookEntity;
import com.globant.udemy.course.graphql_spring_boot.model.dto.BookDto;
import com.globant.udemy.course.graphql_spring_boot.model.dto.BookRequest;
import com.globant.udemy.course.graphql_spring_boot.repository.BookEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final String LOG_PREFIX = "Book Service >>>";
    private final BookEntityRepository bookEntityRepository;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks() {
        log.info("{} Getting all books", LOG_PREFIX);
        return bookEntityRepository.findAllBooks().stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }

    @Override
    public void createBook(BookRequest bookRequest) {
        log.info("{} Validating book request", LOG_PREFIX);
        //TODO validations
        log.info("{} Saving book", LOG_PREFIX);
        bookEntityRepository.save(mapBook(bookRequest));
    }

    @Override
    public String resolveDescription(BookDto bookDto) {
        log.info("{} Generating description", LOG_PREFIX);
        return String.format("%s has %s pages", bookDto.getName(), bookDto.getPageCount());
    }

    private BookEntity mapBook(BookRequest bookRequest) {
        return BookEntity.builder()
                .name(bookRequest.getName())
                .pageCount(bookRequest.getPageCount())
                .authorId(authorService.getReferenceById(bookRequest.getAuthorId()))
                .build();
    }
}
