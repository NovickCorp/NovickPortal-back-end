package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.Category;
import com.novick.customers.menu.repositories.CategoriesRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    private CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public Map<Integer, Category> categories() {
        return categoriesRepository.findAll().stream().collect(Collectors.toMap(Category::getId, Function.identity()));
    }
}
