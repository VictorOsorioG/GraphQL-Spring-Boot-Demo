package com.globant.udemy.course.graphql_spring_boot.controller;

import com.globant.udemy.course.graphql_spring_boot.model.dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class ClientController {

    private final String LOG_PREFIX = "Client REST Controller >>>";
    private final HttpGraphQlClient graphQlClient;

    @Value("${app.graphql.baseurl}")
    private String baseUrl;

    public ClientController() {
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.graphQlClient = HttpGraphQlClient.builder(webClient).build();
    }

    @GetMapping
    public List<BookDto> getBooks() {
        String bookQueryPath = "bookDtos";
        String getBooksQuery = """
                query {
                    bookDtos {
                        name
                        pageCount
                        author {
                            name
                        }
                    }
                }
                """;

        log.info("{} Getting books", LOG_PREFIX);
        return graphQlClient.document(getBooksQuery)
                .retrieve(bookQueryPath)
                .toEntityList(BookDto.class)
                .block();
    }
}
