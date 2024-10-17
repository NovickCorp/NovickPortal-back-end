package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepositorty extends JpaRepository<Meal, Integer> {
}
