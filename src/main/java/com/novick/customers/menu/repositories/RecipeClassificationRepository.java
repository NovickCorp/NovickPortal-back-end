package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.RecipeClassification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeClassificationRepository extends JpaRepository<RecipeClassification, Integer> {

    List<RecipeClassification> findAllByRecipesId(int recipesId);
}
