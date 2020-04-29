package com.kostylenko.config_service.config_service_rest.domain.service;

import com.kostylenko.config_service.config_service_rest.data.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ApplicationService {

    private ApplicationRepository applicationRepository;

    public Set<String> getApplicationNames() {
        return applicationRepository.getApplicationNames();
    }
}
