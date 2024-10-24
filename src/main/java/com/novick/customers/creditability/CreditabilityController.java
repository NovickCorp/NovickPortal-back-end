package com.novick.customers.creditability;

import com.novick.customers.menu.entities.Category;
import com.novick.customers.menu.entities.Meal;
import com.novick.customers.creditability.models.Creditability;
import com.novick.customers.menu.models.Recipe;
import com.novick.customers.menu.service.CategoriesService;
import com.novick.customers.menu.service.MealsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
public class CreditabilityController {

    private final Map<Integer, Category> categories;
    private final Map<String, Category> categoriesByName;
    private final Map<Integer, Meal> meals;

    public CreditabilityController(CategoriesService categoriesService, MealsService mealsService) {
        this.categories = categoriesService.categories();
        this.categoriesByName = categoriesService.categoriesByName();
        this.meals = mealsService.mealMap();
    }

//    Credibility exception for Breakfast and AM and PM Snacks:
//
//    Breakfast:
//
//    If Milk, Fruit or Vegetable and Grain or MMA (all three) – reach creditability – the recipe should be marked creditable on the recipe header. If credibility exceeds on these three or beyond these three ( example items with Fruit in addition to vegetable or vice versa, same with grain and MMA were chosen) add the extra E sign to the credibility indication on the recipe header.
//
//    For AM and PM Snack:
//
//    If any 2 of the 5 components reach credibility - the recipe should be marked creditable
//
//    o   Milk
//
//    o   Vegetable
//
//    o   Fruit
//
//    o   Grain
//
//    o   Meat/Meat Alternate
//
//    If credibility exceeds on the two items selected and if other items are selected in addition to the two add the extra E sign to the credibility indication on the recipe header.
//
    @GetMapping("/creditability")
    public Creditability isCreditability(@RequestBody Recipe recipe) {
        Map<Integer, Double> creditabilityPerCategory = new HashMap<>();
        recipe.getCategories().stream().forEach(c -> creditabilityPerCategory.put(c.getId(), c.getCreditabilityScore()));

        var milk = creditabilityPerCategory.get(categoriesByName.get("Milk").getId());
        var fruit = creditabilityPerCategory.get(categoriesByName.get("Fruit").getId());
        var vegetable = creditabilityPerCategory.get(categoriesByName.get("Vegetable").getId());
        var mma = creditabilityPerCategory.get(categoriesByName.get("MMA").getId());
        var grain = creditabilityPerCategory.get(categoriesByName.get("Grain").getId());

        var meal = meals.get(recipe.getMealPattern().id());
        return switch (meal.getName()) {
            case "Breakfast" -> creditabilityForBreakfast(milk, fruit, vegetable, mma, grain);
            case "AM Snack", "PM Snack" -> creditabilityForSnacks(milk, fruit, vegetable, mma, grain);
            case "Lunch", "Supper" -> creditabilityForLunchSupper(milk, fruit, vegetable, mma, grain);
            default -> throw new AssertionError("Unexpected value: " + meal.getName());
        };
    }

    private Creditability creditabilityForBreakfast(Double milk, Double fruit, Double vegetable, Double mma, Double grain) {
        if (milk < 1.0 || (fruit < 1.0 && vegetable < 1.0) || (mma < 1.0 && grain < 1.0)) {
            return Creditability.NOT_CREDITABLE;
        }

        if (milk > 1.0 && Math.max(fruit, vegetable) > 1.0 && Math.max(mma, grain) > 1.0) {
            return Creditability.EXCEEDED;
        }

        return Creditability.CREDITABLE;
    }

    private Creditability creditabilityForSnacks(Double milk, Double fruit, Double vegetable, Double mma, Double grain) {
        var exceeding = 0;
        for (var score : Stream.of(milk, fruit, vegetable, mma, grain).filter(Objects::nonNull).toList()) {
            if (score < 1.0) {
                return Creditability.NOT_CREDITABLE;
            }
            exceeding += (score > 1.0) ? 1 : 0;
        }
        return (exceeding >= 2) ? Creditability.EXCEEDED : Creditability.CREDITABLE;
    }

    private Creditability creditabilityForLunchSupper(Double milk, Double fruit, Double vegetable, Double mma, Double grain) {
        var exceeding = 0;
        for (var score : Stream.of(milk, fruit, vegetable, mma, grain).filter(Objects::nonNull).toList()) {
            if (score < 1.0) {
                return Creditability.NOT_CREDITABLE;
            }
            exceeding += (score > 1.0) ? 1 : 0;
        }
        return (exceeding > 0) ? Creditability.EXCEEDED : Creditability.CREDITABLE;
    }
}
