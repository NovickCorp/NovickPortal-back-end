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
    private int categoryId;

    @Column(nullable = false)
    private int numberOfItems;

    @Column(nullable = false)
    private String size;

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
