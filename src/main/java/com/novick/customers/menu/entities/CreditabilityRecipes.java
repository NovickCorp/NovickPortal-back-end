package com.novick.customers.menu.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "creditability_recipes")
public class CreditabilityRecipes {

    @EmbeddedId
    private CreditabilityRecipeId id;

    private double creditabilityScore;

    public CreditabilityRecipeId getId() {
        return id;
    }

    public void setId(CreditabilityRecipeId id) {
        this.id = id;
    }

    public double getCreditabilityScore() {
        return creditabilityScore;
    }

    public void setCreditabilityScore(double creditabilityScore) {
        this.creditabilityScore = creditabilityScore;
    }

    public static class CreditabilityRecipeId {

        private int recipeId;

        private int categoryId;

        public int getRecipeId() {
            return recipeId;
        }

        public void setRecipeId(int recipeId) {
            this.recipeId = recipeId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CreditabilityRecipeId that = (CreditabilityRecipeId) o;
            return recipeId == that.recipeId && categoryId == that.categoryId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(recipeId, categoryId);
        }
    }
}
