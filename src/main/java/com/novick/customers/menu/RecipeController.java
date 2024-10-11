package com.novick.customers.menu;

import com.novick.customers.menu.entities.CreditabilityRecipes;
import com.novick.customers.menu.entities.RecipeClassification;
import com.novick.customers.menu.entities.RecipeServingSize;
import com.novick.customers.menu.models.Recipe;
import com.novick.customers.menu.service.CreditabilityRecipeService;
import com.novick.customers.menu.service.RecipeClassificationService;
import com.novick.customers.menu.service.RecipeService;
import com.novick.customers.menu.service.RecipeServingSizeService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {

    private RecipeService recipeService;
    private RecipeClassificationService recipeClassificationService;
    private CreditabilityRecipeService creditabilityRecipeService;
    private RecipeServingSizeService recipeServingSizeService;

    public RecipeController(RecipeService recipeService, RecipeClassificationService recipeClassificationService, CreditabilityRecipeService creditabilityRecipeService, RecipeServingSizeService recipeServingSizeService) {
        this.recipeService = recipeService;
        this.recipeClassificationService = recipeClassificationService;
        this.creditabilityRecipeService = creditabilityRecipeService;
        this.recipeServingSizeService = recipeServingSizeService;
    }

    @GetMapping("/recipes/{id}")
    public List<Recipe> getRecipes(@PathVariable String id) {
        return recipeService.findAllByOid(id);
    }

    @Transactional
    @PostMapping("/recipes")
    public String addRecipe(@RequestBody Recipe recipe) {
        try {
            var recipeEntity = new com.novick.customers.menu.entities.Recipe();
            recipeEntity.setOid(recipe.getOid());
            recipeEntity.setName(recipe.getName());
            recipeEntity.setAgeGroupId(recipe.getAgeGroup().id());
            recipeEntity.setMealId(recipe.getMealPattern().id());
            var saved = recipeService.save(recipeEntity);

            recipe.getClassifications().forEach(classification -> {
                var classificationEntity = new RecipeClassification();
                classificationEntity.setRecipesId(saved.getId());
                classificationEntity.setClassificationId(classification.id());
                recipeClassificationService.save(classificationEntity);
            });

            recipe.getCategories().forEach(category -> {
                var creditabilityEntity = new CreditabilityRecipes();
                var id = new CreditabilityRecipes.CreditabilityRecipeId();
                id.setRecipeId(saved.getId());
                id.setCategoryId(category.getId());
                creditabilityEntity.setId(id);
                creditabilityEntity.setCreditabilityScore(category.getCreditabilityScore());
                creditabilityRecipeService.save(creditabilityEntity);
            });

            recipe.getCategories().forEach(category -> {
                Optional.ofNullable(category.getIngredients()).orElse(Collections.emptyList()).forEach(ingredient -> {
                    var recipeServingSizeEntity = new RecipeServingSize();
                    recipeServingSizeEntity.setRecipesId(saved.getId());
                    recipeServingSizeEntity.setCategoryId(ingredient.categoryId());
                    recipeServingSizeEntity.setServingSizesId(ingredient.id());
                    recipeServingSizeEntity.setNumberOfItems(ingredient.numberOfItems());
                    recipeServingSizeEntity.setSize(ingredient.size());
                    recipeServingSizeService.save(recipeServingSizeEntity);
                });
            });

            return "Recipe " + saved.getId() + " added";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
