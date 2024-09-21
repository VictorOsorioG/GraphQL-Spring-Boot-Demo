package com.globant.udemy.course.graphql_spring_boot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Table(name = "author")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @OneToMany(mappedBy = "authorId", fetch = FetchType.LAZY)
    @JsonIgnore
    List<BookEntity> books;
}
