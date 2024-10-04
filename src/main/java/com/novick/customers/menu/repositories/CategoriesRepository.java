package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {
}
