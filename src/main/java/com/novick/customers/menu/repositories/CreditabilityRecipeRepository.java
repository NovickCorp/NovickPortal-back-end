package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.CreditabilityRecipes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditabilityRecipeRepository extends JpaRepository<CreditabilityRecipes, Integer> {

    List<CreditabilityRecipes> findAllByIdRecipeId(int recipeId);

}
