package com.globant.udemy.course.graphql_spring_boot.repository;

import com.globant.udemy.course.graphql_spring_boot.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    @Query("SELECT b FROM BookEntity b")
    List<BookEntity> findAllBooks();
}
