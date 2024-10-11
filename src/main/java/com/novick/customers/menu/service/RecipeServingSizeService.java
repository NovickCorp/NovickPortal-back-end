package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.RecipeServingSize;
import com.novick.customers.menu.repositories.RecipeServiceSizeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeServingSizeService {

    private RecipeServiceSizeRepository recipeServiceSizeRepository;

    public RecipeServingSizeService(RecipeServiceSizeRepository recipeServiceSizeRepository) {
        this.recipeServiceSizeRepository = recipeServiceSizeRepository;
    }

    public RecipeServingSize save(RecipeServingSize recipeServingSize) {
        return recipeServiceSizeRepository.save(recipeServingSize);
    }
}
