package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.Category;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.util.annotation.NonNull;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {

    @Cacheable("categories")
    @NonNull List<Category> findAll(@NonNull Sort sort);

}
