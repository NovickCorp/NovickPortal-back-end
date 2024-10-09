package com.novick.customers.menu;

import com.novick.customers.menu.models.Recipe;
import com.novick.customers.menu.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes/{id}")
    public List<Recipe> getRecipes(@PathVariable String id) {
        return recipeService.findAllByOid(id);
    }
}
