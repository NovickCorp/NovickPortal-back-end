package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.RecipeClassification;
import com.novick.customers.menu.repositories.RecipeClassificationRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeClassificationService {

    private RecipeClassificationRepository recipeClassificationRepository;

    public RecipeClassificationService(RecipeClassificationRepository recipeClassificationRepository) {
        this.recipeClassificationRepository = recipeClassificationRepository;
    }

    public RecipeClassification save(RecipeClassification recipeClassification) {
        return recipeClassificationRepository.save(recipeClassification);
    }
}
