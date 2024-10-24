package com.novick.customers.planner.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.novick.customers.menu.entities.AgeGroup;

import java.time.LocalDate;
import java.util.List;

public record Menu(@JsonFormat(pattern = "yyyy-MM-dd") LocalDate date, String name, String userId, MealCalendar mealCalendar, Servings individualsPerGroup, List<AgeGroup> ageGroups) {
}
