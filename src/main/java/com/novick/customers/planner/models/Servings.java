package com.novick.customers.planner.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Servings(@JsonProperty("ages-1-2") Integer ages1to2, @JsonProperty("ages-3-5") Integer ages3to5, @JsonProperty("ages-6-18") Integer ages6to18, Integer adult) {
}
