package com.globant.udemy.course.graphql_spring_boot.config;

import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.collection.spi.PersistentList;
import org.hibernate.proxy.HibernateProxy;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(
                mappingContext -> {
                    if (mappingContext.getSource() instanceof HibernateProxy
                            && ((HibernateProxy) mappingContext.getSource()).getHibernateLazyInitializer().isUninitialized()) {
                        return false;
                    }
                    if (mappingContext.getSource() instanceof PersistentCollection
                            && !((PersistentCollection<?>) mappingContext.getSource()).wasInitialized()) {
                        return false;
                    }
                    if (mappingContext.getSource() instanceof PersistentList
                            && !((PersistentList<?>) mappingContext.getSource()).wasInitialized()) {
                        return false;
                    }
                    return true;
                }
        );
        return new ModelMapper();
    }
}
