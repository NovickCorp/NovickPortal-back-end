package com.novick.customers.menu.service;

import com.novick.customers.menu.entities.AgeGroup;
import com.novick.customers.menu.entities.Classification;
import com.novick.customers.menu.entities.Meal;
import com.novick.customers.menu.entities.ServingSize;
import com.novick.customers.menu.models.Recipe;
import com.novick.customers.menu.repositories.RecipeClassificationRepository;
import com.novick.customers.menu.repositories.RecipeRepository;
import com.novick.customers.menu.repositories.RecipeServiceSizeRepository;
import com.novick.customers.models.IdValue;
import com.novick.customers.models.ParameterValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public final class RecipeService {

    private final Map<Integer, Meal> mealsMap;
    private final Map<Integer, AgeGroup> ageGroupsMap;
    private final Map<Integer,ServingSize> servingSizes;
    private RecipeRepository recipeRepository;
    private Map<Integer,Classification> classificationMap;
    private RecipeClassificationRepository recipeClassificationRepository;
    private RecipeServiceSizeRepository recipeServiceSizeRepository;

    public RecipeService(RecipeRepository recipeRepository, ClassificationService classificationService, RecipeClassificationRepository recipeClassificationRepository, RecipeServiceSizeRepository recipeServiceSizeRepository, MealsService mealsService, AgeGroupService ageGroupService, ServingSizeService servingSizeService) {
        this.recipeRepository = recipeRepository;
        this.classificationMap = classificationService.classificationMap();
        this.recipeClassificationRepository = recipeClassificationRepository;
        this.recipeServiceSizeRepository = recipeServiceSizeRepository;
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
                                   return new Recipe.Builder(entity.getId())
                                           .name(entity.getName())
                                           .oid(entity.getOid())
                                           .mealPattern(new ParameterValue(entity.getMealId(), mealsMap.get(entity.getMealId()).getName(), mealsMap.get(entity.getMealId()).getParameterName()))
                                           .ageGroup(new ParameterValue(entity.getAgeGroupId(), ageGroupsMap.get(entity.getAgeGroupId()).getName(), ageGroupsMap.get(entity.getAgeGroupId()).getParameterName()))
                                           .ingredients(ingredients.stream().map(i -> new IdValue(i.getServingSizesId(), servingSizes.get(i.getServingSizesId()).getOptionValue())).toList())
                                           .classifications(classifications.stream().map(c -> classificationMap.get(c.getClassificationsId())).map(c2 -> new ParameterValue(c2.getId(), c2.getName(), c2.getParameterName())).toList())
                                           .build();
                               })
                               .toList();
    }
}
