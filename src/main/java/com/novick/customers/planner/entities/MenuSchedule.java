package com.novick.customers.planner.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MenuSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int menuId;
    private int recipesId;
    private int ageGroupsId;
    private int mealsId;
    private int week;
    private int day;

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

    public void setAgeGroupsId(int ageGroupsId) {
        this.ageGroupsId = ageGroupsId;
    }

    public int getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(int recipesId) {
        this.recipesId = recipesId;
    }

    public int getMealsId() {
        return mealsId;
    }

    public void setMealsId(int mealsId) {
        this.mealsId = mealsId;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
