package com.novick.customers.menu.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes_service_sizes")
public class RecipeServingSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int servingSizesId;

    @Column(nullable = false)
    private int recipesId;

    @Column(nullable = false)
    private int numberOfItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getServingSizesId() {
        return servingSizesId;
    }

    public void setServingSizesId(int servingSizesId) {
        this.servingSizesId = servingSizesId;
    }

    public int getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(int recipesId) {
        this.recipesId = recipesId;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }
}
