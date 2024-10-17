package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.RecipeServingSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeServiceSizeRepository extends JpaRepository<RecipeServingSize, Integer> {

    List<RecipeServingSize> findAllByRecipesId(int recipesId);
    void deleteAllByRecipesId(int recipesId);

}
