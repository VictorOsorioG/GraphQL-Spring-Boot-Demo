package com.globant.udemy.course.graphql_spring_boot.exception;

import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerAdvisor {

    @GraphQlExceptionHandler
    public GraphQLError handleError(RuntimeException e) {
        return GraphQLError.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(e.getMessage())
                .build();
    }
}
