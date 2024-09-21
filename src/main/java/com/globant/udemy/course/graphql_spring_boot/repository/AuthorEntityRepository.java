package com.globant.udemy.course.graphql_spring_boot.repository;

import com.globant.udemy.course.graphql_spring_boot.model.AuthorEntity;
import com.globant.udemy.course.graphql_spring_boot.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorEntityRepository extends JpaRepository<AuthorEntity, Long> {

    @Query("""
            SELECT b.authorId
            FROM BookEntity b
            WHERE b.id = :id
            """)
    Optional<AuthorEntity> findByBookId(@Param("id") Long id);
}
