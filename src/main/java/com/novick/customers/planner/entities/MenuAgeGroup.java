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
    private int ageGroupsId;
    private int servings;

    public static MenuAgeGroup create(int menuId, int ageGroupsId, int servings) {
        var entity = new MenuAgeGroup();
        entity.setMenuId(menuId);
        entity.setAgeGroupsId(ageGroupsId);
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

    public int getAgeGroupsId() {
        return ageGroupsId;
    }

    public void setAgeGroupsId(int ageGroupId) {
        this.ageGroupsId = ageGroupId;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
