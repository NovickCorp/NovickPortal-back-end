package com.novick.customers.menu.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes_classifications")
public class RecipeClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int recipesId;

    private int classificationsId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(int recipeId) {
        this.recipesId = recipeId;
    }

    public int getClassificationsId() {
        return classificationsId;
    }

    public void setClassificationId(int classificationsId) {
        this.classificationsId = classificationsId;
    }
}
