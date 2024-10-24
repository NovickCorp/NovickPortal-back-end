package com.novick.customers.planner.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MealCalendar(WeekCalendar week1, WeekCalendar week2, WeekCalendar week3, WeekCalendar week4) {
}
