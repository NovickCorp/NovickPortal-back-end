package com.novick.customers.menu.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String oid;

    private String name;

    private int mealId;

    private int ageGroupId;

    private boolean isCreditable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getAgeGroupId() {
        return ageGroupId;
    }

    public void setAgeGroupId(int ageGroupId) {
        this.ageGroupId = ageGroupId;
    }

    public boolean getIsCreditable() {
        return isCreditable;
    }

    public void setIsCreditable(boolean creditable) {
        isCreditable = creditable;
    }
}
