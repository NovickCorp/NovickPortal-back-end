package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.*;
import com.novick.customers.menu.models.Category;
import com.novick.customers.menu.models.Ingredients;
import com.novick.customers.menu.models.Recipe;
import com.novick.customers.menu.repositories.CreditabilityRecipeRepository;
import com.novick.customers.menu.repositories.RecipeClassificationRepository;
import com.novick.customers.menu.repositories.RecipeRepository;
import com.novick.customers.menu.repositories.RecipeServiceSizeRepository;
import com.novick.customers.models.ParameterValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public final class RecipeService {

    private final Map<Integer, Meal> mealsMap;
    private final Map<Integer, AgeGroup> ageGroupsMap;
    private final Map<Integer,ServingSize> servingSizes;
    private RecipeRepository recipeRepository;
    private Map<Integer,Classification> classificationMap;
    private RecipeClassificationRepository recipeClassificationRepository;
    private RecipeServiceSizeRepository recipeServiceSizeRepository;
    private CreditabilityRecipeRepository creditabilityRecipeRepository;
    private CategoriesService categoriesService;

    public RecipeService(RecipeRepository recipeRepository, ClassificationService classificationService, RecipeClassificationRepository recipeClassificationRepository, RecipeServiceSizeRepository recipeServiceSizeRepository, MealsService mealsService, AgeGroupService ageGroupService, ServingSizeService servingSizeService, CreditabilityRecipeRepository creditabilityRecipeRepository, CategoriesService categoriesService) {
        this.recipeRepository = recipeRepository;
        this.classificationMap = classificationService.classificationMap();
        this.recipeClassificationRepository = recipeClassificationRepository;
        this.recipeServiceSizeRepository = recipeServiceSizeRepository;
        this.creditabilityRecipeRepository = creditabilityRecipeRepository;
        this.categoriesService = categoriesService;

        this.mealsMap = mealsService.mealMap();
        this.ageGroupsMap = ageGroupService.ageGroupMap();
        this.servingSizes = servingSizeService.servingSizeMap();
    }

    public List<Recipe> findAllByOid(String oid) {
        return recipeRepository.findAllByOid(oid)
                               .stream()
                               .map(entity -> {
                                   var ingredients = recipeServiceSizeRepository.findAllByRecipesId(entity.getId());
                                   var classifications = recipeClassificationRepository.findAllByRecipesId(entity.getId());
                                   var scores = creditabilityRecipeRepository.findAllByIdRecipeId(entity.getId());
                                   return new Recipe.Builder(entity.getId())
                                           .name(entity.getName())
                                           .oid(entity.getOid())
                                           .mealPattern(new ParameterValue(entity.getMealId(), mealsMap.get(entity.getMealId()).getName(), mealsMap.get(entity.getMealId()).getParameterName()))
                                           .ageGroup(new ParameterValue(entity.getAgeGroupId(), ageGroupsMap.get(entity.getAgeGroupId()).getName(), ageGroupsMap.get(entity.getAgeGroupId()).getParameterName()))
                                           .categories(getCategories(ingredients, scores))
                                           .classifications(classifications.stream().map(c -> classificationMap.get(c.getClassificationsId())).map(c2 -> new ParameterValue(c2.getId(), c2.getName(), c2.getParameterName())).toList())
                                           .build();
                               })
                               .toList();
    }

    private List<Category> getCategories(List<RecipeServingSize> ingredients, List<CreditabilityRecipes> scores) {
        var list = ingredients.stream()
                              .map(i -> new Ingredients(i.getServingSizesId(), i.getCategoryId(), servingSizes.get(i.getServingSizesId()).getOptionValue(), i.getSize()))
                              .collect(Collectors.groupingBy(Ingredients::categoryId));

        var categoriesMap = categoriesService.categories();
        return scores.stream().map(i -> new Category(i.getId().getCategoryId(), categoriesMap.get(i.getId().getCategoryId()).getName(), i.getCreditabilityScore(), list.get(i.getId().getCategoryId()))).toList();
    }
}
