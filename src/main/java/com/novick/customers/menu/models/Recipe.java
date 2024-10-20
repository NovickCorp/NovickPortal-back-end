package com.novick.customers.menu.models;

import com.novick.customers.models.ParameterValue;

import java.util.List;

public final class Recipe {

    private int id;
    private String name;
    private String oid;
    private boolean isCreditable;
    private ParameterValue mealPattern;
    private ParameterValue ageGroup;
    private List<ParameterValue> classifications;
    private List<Category> categories;

    public Recipe() {}

    private Recipe(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.oid = builder.oid;
        this.isCreditable = builder.isCreditable;
        this.mealPattern = builder.mealPattern;
        this.ageGroup = builder.ageGroup;
        this.classifications = builder.classifications;
        this.categories = builder.ingredients;
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

    public boolean getIsCreditable() {
        return isCreditable;
    }

    public ParameterValue getMealPattern() {
        return mealPattern;
    }

    public ParameterValue getAgeGroup() {
        return ageGroup;
    }

    public List<ParameterValue> getClassifications() {
        return classifications;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setCreditable(boolean creditable) {
        isCreditable = creditable;
    }

    public void setMealPattern(ParameterValue mealPattern) {
        this.mealPattern = mealPattern;
    }

    public void setAgeGroup(ParameterValue ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void setClassifications(List<ParameterValue> classifications) {
        this.classifications = classifications;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public static class Builder {

        private int id;
        private String name;
        private String oid;
        private boolean isCreditable;
        private ParameterValue mealPattern;
        private ParameterValue ageGroup;
        private List<ParameterValue> classifications;
        private List<Category> ingredients;

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

        public Builder isCreditable(boolean isCreditable) {
            this.isCreditable = isCreditable;
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

        public Builder classifications(List<ParameterValue> classifications) {
            this.classifications = List.copyOf(classifications);
            return this;
        }

        public Builder categories(List<Category> categories) {
            this.ingredients = List.copyOf(categories);
            return this;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }
}
