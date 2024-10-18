package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.Classification;
import com.novick.customers.menu.repositories.ClassificationRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ClassificationService {

    private final ClassificationRepository classificationRepository;

    public ClassificationService(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Cacheable("classifications")
    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }

    public Map<Integer, Classification> classificationMap() {
        return classificationRepository.findAll()
                                       .stream()
                                       .collect(Collectors.toMap(Classification::getId, Function.identity()));
    }
}
