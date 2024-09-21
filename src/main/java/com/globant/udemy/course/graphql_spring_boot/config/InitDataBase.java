package com.globant.udemy.course.graphql_spring_boot.config;

import com.globant.udemy.course.graphql_spring_boot.model.AuthorEntity;
import com.globant.udemy.course.graphql_spring_boot.model.BookEntity;
import com.globant.udemy.course.graphql_spring_boot.repository.AuthorEntityRepository;
import com.globant.udemy.course.graphql_spring_boot.repository.BookEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class InitDataBase {

    @Bean
    @Transactional
    CommandLineRunner init(BookEntityRepository bookEntityRepository, AuthorEntityRepository authorEntityRepository) {
        return args -> {
            AuthorEntity authorArthur = AuthorEntity.builder()
                    .name("Arthur Conan Doyle")
                    .build();
            authorEntityRepository.save(authorArthur);

            AuthorEntity authorEdgar = AuthorEntity.builder()
                    .name("Edgar Allan Poe")
                    .build();
            authorEntityRepository.save(authorEdgar);

            BookEntity bookSherlock = BookEntity.builder()
                    .name("Sherlock Holmes The Complete Collection")
                    .pageCount(1906)
                    .authorId(authorArthur)
                    .build();

            BookEntity bookBlackCat = BookEntity.builder()
                    .name("The Black Cat")
                    .pageCount(34)
                    .authorId(authorEdgar)
                    .build();

            BookEntity bookFallOfUsher = BookEntity.builder()
                    .name("The Fall of the House of User")
                    .pageCount(26)
                    .authorId(authorEdgar)
                    .build();

            bookEntityRepository.save(bookSherlock);
            bookEntityRepository.save(bookBlackCat);
            bookEntityRepository.save(bookFallOfUsher);
        };
    }
}
