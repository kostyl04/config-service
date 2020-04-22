package com.kostylenko.config_service.config_service_rest.data.repository;

import com.kostylenko.config_service.config_service_rest.data.entity.Meta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends MongoRepository<Meta, String> {

    boolean existsByName(String name);

    Meta findByName(String name);
}