package com.novick.customers.menu.repositories;

import com.novick.customers.menu.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findAllByOid(String oid);
}
