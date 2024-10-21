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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
    @DeleteMapping("/recipes/{id}/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String id, @PathVariable int recipeId) {
        try {
            var entity = recipeService.findById(recipeId);
            if (entity.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            var recipe = entity.get();
            if (!Objects.equals(id, recipe.getOid())) {
                return new ResponseEntity<>("This request is trying to delete a recipe that doesn't belong to the user", HttpStatus.BAD_REQUEST);
            }

            recipeClassificationService.deleteAllByRecipeId(recipeId);
            creditabilityRecipeService.deleteAllByRecipeId(recipeId);
            recipeServingSizeService.deleteAllByRecipeId(recipeId);
            recipeService.deleteById(recipeId);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PostMapping("/recipes/{id}/{recipeId}")
    public ResponseEntity<String> updateRecipe(@RequestBody Recipe recipe, @PathVariable String id, @PathVariable int recipeId) {
        if (!recipe.getOid().equals(id)) {
            return new ResponseEntity<>("OID in request doesn't match the OID in the request body", HttpStatus.BAD_REQUEST);
        }
        if (recipe.getId() != recipeId) {
            return new ResponseEntity<>("Recipe Id in request doesn't match the Recipe Id in the request body", HttpStatus.BAD_REQUEST);
        }

        try {
            var recipeEntity = new com.novick.customers.menu.entities.Recipe();
            recipeEntity.setId(recipe.getId());
            recipeEntity.setOid(recipe.getOid());
            recipeEntity.setName(recipe.getName());
            recipeEntity.setAgeGroupId(recipe.getAgeGroup().id());
            recipeEntity.setMealId(recipe.getMealPattern().id());
            recipeEntity.setIsCreditable(recipe.getIsCreditable());
            recipeService.save(recipeEntity);

            recipeClassificationService.deleteAllByRecipeId(recipe.getId());
            recipe.getClassifications().forEach(classification -> {
                var classificationEntity = new RecipeClassification();
                classificationEntity.setRecipesId(recipe.getId());
                classificationEntity.setClassificationId(classification.id());
                recipeClassificationService.save(classificationEntity);
            });

            creditabilityRecipeService.deleteAllByRecipeId(recipeId);
            recipe.getCategories().forEach(category -> {
                var creditabilityEntity = new CreditabilityRecipes();
                var cid = new CreditabilityRecipes.CreditabilityRecipeId();
                cid.setRecipeId(recipe.getId());
                cid.setCategoryId(category.getId());
                creditabilityEntity.setId(cid);
                creditabilityEntity.setCreditabilityScore(category.getCreditabilityScore());
                creditabilityRecipeService.save(creditabilityEntity);
            });

            recipeServingSizeService.deleteAllByRecipeId(recipeId);
            recipe.getCategories().forEach(category -> {
                Optional.ofNullable(category.getIngredients()).orElse(Collections.emptyList()).forEach(ingredient -> {
                    var recipeServingSizeEntity = new RecipeServingSize();
                    recipeServingSizeEntity.setRecipesId(recipe.getId());
                    recipeServingSizeEntity.setCategoryId(ingredient.categoryId());
                    recipeServingSizeEntity.setServingSizesId(ingredient.id());
                    recipeServingSizeEntity.setNumberOfItems(ingredient.numberOfItems());
                    recipeServingSizeEntity.setSize(ingredient.size());
                    recipeServingSizeService.save(recipeServingSizeEntity);
                });
            });

            return new ResponseEntity<>("Recipe " + recipe.getName() + " was updated", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @Transactional
    @PostMapping("/recipes")
    public ResponseEntity<String> addRecipe(@RequestBody Recipe recipe) {
        try {
            var recipeEntity = new com.novick.customers.menu.entities.Recipe();
            recipeEntity.setOid(recipe.getOid());
            recipeEntity.setName(recipe.getName());
            recipeEntity.setAgeGroupId(recipe.getAgeGroup().id());
            recipeEntity.setMealId(recipe.getMealPattern().id());
            recipeEntity.setIsCreditable(recipe.getIsCreditable());
            var saved = recipeService.save(recipeEntity);

            recipe.getClassifications().forEach(classification -> {
                var classificationEntity = new RecipeClassification();
                classificationEntity.setRecipesId(saved.getId());
                classificationEntity.setClassificationId(classification.id());
                recipeClassificationService.save(classificationEntity);
            });

            var recipeCategories = recipe.getCategories();
            var categories = recipeCategories.stream()
                                             .filter(category -> category.getIngredients()
                                                                         .stream()
                                                                         .noneMatch(ingredient -> Objects.isNull(ingredient.id())))
                                             .toList();

            categories.forEach(category -> {
                var creditabilityEntity = new CreditabilityRecipes();
                var id = new CreditabilityRecipes.CreditabilityRecipeId();
                id.setRecipeId(saved.getId());
                id.setCategoryId(category.getId());
                creditabilityEntity.setId(id);
                creditabilityEntity.setCreditabilityScore(category.getCreditabilityScore());
                creditabilityRecipeService.save(creditabilityEntity);
            });

            categories.forEach(category -> {
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

            return new ResponseEntity<>("Recipe " + saved.getId() + " added", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
}
