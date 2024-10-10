package com.novick.customers.menu.models;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int id;
    private String name;
    private double creditabilityScore;
    private List<Ingredients> ingredients;

    public Category(int id, String name, double creditabilityScore, List<Ingredients> ingredients) {
        this.id = id;
        this.name = name;
        this.creditabilityScore = creditabilityScore;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCreditabilityScore() {
        return creditabilityScore;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }
}
