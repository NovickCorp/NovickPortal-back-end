package com.novick.customers.planner.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AgeGroupCalendar(@JsonProperty("ages-1-2") MealCalendar ages1to2, @JsonProperty("ages-3-5") MealCalendar ages3to5, @JsonProperty("ages-6-18") MealCalendar ages6to18, MealCalendar adult) {
}
