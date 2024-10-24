package com.novick.customers.planner.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novick.customers.menu.models.Recipe;

public record DayCalendar(Recipe breakfast, @JsonProperty("am-snack") Recipe amSnack, Recipe lunch, @JsonProperty("pm-snack") Recipe pmSnack, Recipe supper) {
}
