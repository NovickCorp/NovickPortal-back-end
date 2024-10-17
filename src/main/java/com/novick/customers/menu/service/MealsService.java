package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.Meal;
import com.novick.customers.menu.repositories.MealRepositorty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MealsService {

    private MealRepositorty mealRepositorty;

    public MealsService(MealRepositorty mealRepositorty) {
        this.mealRepositorty = mealRepositorty;
    }

    @Cacheable("meals")
    public List<Meal> findAll() {
        return mealRepositorty.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    public Map<Integer, Meal> mealMap() {
        return findAll().stream().collect(Collectors.toMap(Meal::getId, Function.identity()));
    }
}
