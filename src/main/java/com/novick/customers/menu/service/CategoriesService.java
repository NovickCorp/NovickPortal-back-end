package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.Category;
import com.novick.customers.menu.repositories.CategoriesRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    private CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Cacheable("categories")
    public List<Category> findAll() {
        return categoriesRepository.findAll(Sort.by(Sort.Direction.ASC, "priority")).stream().filter(c -> Objects.nonNull(c.getParameterName())).toList();
    }

    public Map<Integer, Category> categories() {
        return categoriesRepository.findAll().stream().collect(Collectors.toMap(Category::getId, Function.identity()));
    }
}
