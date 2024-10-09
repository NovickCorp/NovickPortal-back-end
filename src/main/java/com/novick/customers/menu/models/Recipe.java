package com.novick.customers.menu.models;

import com.novick.customers.models.IdValue;
import com.novick.customers.models.ParameterValue;

import java.util.List;

public final class Recipe {

    private final int id;
    private final String name;
    private final String oid;
    private final ParameterValue mealPattern;
    private final ParameterValue ageGroup;
    private final List<IdValue> classifications;
    private final List<IdValue> ingredients;

    private Recipe(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.oid = builder.oid;
        this.mealPattern = builder.mealPattern;
        this.ageGroup = builder.ageGroup;
        this.classifications = builder.classifications;
        this.ingredients = builder.ingredients;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOid() {
        return oid;
    }

    public ParameterValue getMealPattern() {
        return mealPattern;
    }

    public ParameterValue getAgeGroup() {
        return ageGroup;
    }

    public List<IdValue> getClassifications() {
        return classifications;
    }

    public List<IdValue> getIngredients() {
        return ingredients;
    }

    public static class Builder {

        private int id;
        private String name;
        private String oid;
        private ParameterValue mealPattern;
        private ParameterValue ageGroup;
        private List<IdValue> classifications;
        private List<IdValue> ingredients;

        public Builder(int id) {
            this.id = id;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder oid(String oid) {
            this.oid = oid;
            return this;
        }

        public Builder mealPattern(ParameterValue mealPattern) {
            this.mealPattern = mealPattern;
            return this;
        }

        public Builder ageGroup(ParameterValue ageGroup) {
            this.ageGroup = ageGroup;
            return this;
        }

        public Builder classifications(List<IdValue> classifications) {
            this.classifications = List.copyOf(classifications);
            return this;
        }

        public Builder ingredients(List<IdValue> ingredients) {
            this.ingredients = List.copyOf(ingredients);
            return this;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }
}
