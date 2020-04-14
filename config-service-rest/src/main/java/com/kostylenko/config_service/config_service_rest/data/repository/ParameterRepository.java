package com.kostylenko.config_service.config_service_rest.data.repository;

import com.kostylenko.config_service.config_service_rest.data.entity.Parameter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends MongoRepository<Parameter, String> {
}
