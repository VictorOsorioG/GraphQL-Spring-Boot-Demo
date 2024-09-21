package com.globant.udemy.course.graphql_spring_boot.service;

import com.globant.udemy.course.graphql_spring_boot.model.AuthorEntity;
import com.globant.udemy.course.graphql_spring_boot.model.dto.AuthorDto;
import com.globant.udemy.course.graphql_spring_boot.repository.AuthorEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final String LOG_PREFIX = "Author Service >>>";
    private final AuthorEntityRepository authorEntityRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public AuthorDto getAuthorByBookId(Long id) {
        log.info("{} Getting author of book: {}", LOG_PREFIX, id);
        return authorEntityRepository.findByBookId(id)
                .map(authorEntity -> modelMapper.map(authorEntity, AuthorDto.class))
                .orElseThrow();
    }

    @Override
    public AuthorEntity getReferenceById(Long authorId) {
        log.info("{} Getting author reference", LOG_PREFIX);
        return authorEntityRepository.getReferenceById(authorId);
    }
}
