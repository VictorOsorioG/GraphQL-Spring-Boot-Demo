package com.globant.udemy.course.graphql_spring_boot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    private String name;
    private Integer pageCount;
    private Long authorId;
}
