package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.ServingSize;
import com.novick.customers.menu.repositories.ServingSizeRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ServingSizeService {

    private ServingSizeRepository servingSizeRepository;

    public ServingSizeService(ServingSizeRepository servingSizeRepository) {
        this.servingSizeRepository = servingSizeRepository;
    }

    @Cacheable("ingredients")
    public List<ServingSize> findAll() {
        return servingSizeRepository.findAll();
    }

    public Map<Integer, ServingSize> servingSizeMap() {
        return findAll().stream().collect(Collectors.toMap(ServingSize::getId, Function.identity()));
    }
}
