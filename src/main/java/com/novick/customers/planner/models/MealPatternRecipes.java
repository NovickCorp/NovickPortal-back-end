package com.novick.customers.planner.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novick.customers.menu.entities.Recipe;

public record MealPatternRecipes(Recipe breakfast, @JsonProperty("am-snack") Recipe amSnack, Recipe lunch, @JsonProperty("pm-snack") Recipe pmSnack, Recipe supper) {
}
