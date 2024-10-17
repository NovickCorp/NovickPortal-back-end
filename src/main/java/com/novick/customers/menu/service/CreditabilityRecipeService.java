package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.CreditabilityRecipes;
import com.novick.customers.menu.repositories.CreditabilityRecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditabilityRecipeService {

    private CreditabilityRecipeRepository creditabilityRecipeRepository;

    public CreditabilityRecipeService(CreditabilityRecipeRepository creditabilityRecipeRepository) {
        this.creditabilityRecipeRepository = creditabilityRecipeRepository;
    }

    public CreditabilityRecipes save(CreditabilityRecipes creditabilityRecipes) {
        return creditabilityRecipeRepository.save(creditabilityRecipes);
    }

    public void deleteAllByRecipeId(int recipeId) {
        creditabilityRecipeRepository.deleteAllByIdRecipeId(recipeId);
    }
}
