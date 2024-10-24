package com.novick.customers.planner.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MenuAgeGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int menuId;
    private int ageGroupId;
    private int servings;

    public static MenuAgeGroup create(int menuId, int ageGroupId, int servings) {
        var entity = new MenuAgeGroup();
        entity.setMenuId(menuId);
        entity.setAgeGroupId(ageGroupId);
        entity.setServings(servings);
        return entity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getAgeGroupId() {
        return ageGroupId;
    }

    public void setAgeGroupId(int ageGroupId) {
        this.ageGroupId = ageGroupId;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
