package com.novick.customers.menu.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "creditability_table")
public class CreditableTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int categoryId;
    private double breakfastAgesOneToTwo;
    private double breakfastAgesThreeToFive;
    private double breakfastAgesSixToEighteen;
    private double breakfastAdult;
    private double lunchSupperAgesOneToTwo;
    private double lunchSupperAgesThreeToFive;
    private double lunchSupperAgesSixToEighteen;
    private double lunchSupperAdult;
    private double snackAgesOneToTwo;
    private double snackAgesThreeToFive;
    private double snackAgesSixToEighteen;
    private double snackAdult;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getBreakfastAgesOneToTwo() {
        return breakfastAgesOneToTwo;
    }

    public void setBreakfastAgesOneToTwo(double breakfastAgesOneToTwo) {
        this.breakfastAgesOneToTwo = breakfastAgesOneToTwo;
    }

    public double getBreakfastAgesThreeToFive() {
        return breakfastAgesThreeToFive;
    }

    public void setBreakfastAgesThreeToFive(double breakfastAgesThreeToFive) {
        this.breakfastAgesThreeToFive = breakfastAgesThreeToFive;
    }

    public double getBreakfastAgesSixToEighteen() {
        return breakfastAgesSixToEighteen;
    }

    public void setBreakfastAgesSixToEighteen(double breakfastAgesSixToEighteen) {
        this.breakfastAgesSixToEighteen = breakfastAgesSixToEighteen;
    }

    public double getBreakfastAdult() {
        return breakfastAdult;
    }

    public void setBreakfastAdult(double breakfastAdult) {
        this.breakfastAdult = breakfastAdult;
    }

    public double getLunchSupperAgesOneToTwo() {
        return lunchSupperAgesOneToTwo;
    }

    public void setLunchSupperAgesOneToTwo(double lunchSupperAgesOneToTwo) {
        this.lunchSupperAgesOneToTwo = lunchSupperAgesOneToTwo;
    }

    public double getLunchSupperAgesThreeToFive() {
        return lunchSupperAgesThreeToFive;
    }

    public void setLunchSupperAgesThreeToFive(double lunchSupperAgesThreeToFive) {
        this.lunchSupperAgesThreeToFive = lunchSupperAgesThreeToFive;
    }

    public double getLunchSupperAgesSixToEighteen() {
        return lunchSupperAgesSixToEighteen;
    }

    public void setLunchSupperAgesSixToEighteen(double lunchSupperAgesSixToEighteen) {
        this.lunchSupperAgesSixToEighteen = lunchSupperAgesSixToEighteen;
    }

    public double getLunchSupperAdult() {
        return lunchSupperAdult;
    }

    public void setLunchSupperAdult(double lunchSupperAdult) {
        this.lunchSupperAdult = lunchSupperAdult;
    }

    public double getSnackAgesOneToTwo() {
        return snackAgesOneToTwo;
    }

    public void setSnackAgesOneToTwo(double snackAgesOneToTwo) {
        this.snackAgesOneToTwo = snackAgesOneToTwo;
    }

    public double getSnackAgesThreeToFive() {
        return snackAgesThreeToFive;
    }

    public void setSnackAgesThreeToFive(double snackAgesThreeToFive) {
        this.snackAgesThreeToFive = snackAgesThreeToFive;
    }

    public double getSnackAgesSixToEighteen() {
        return snackAgesSixToEighteen;
    }

    public void setSnackAgesSixToEighteen(double snackAgesSixToEighteen) {
        this.snackAgesSixToEighteen = snackAgesSixToEighteen;
    }

    public double getSnackAdult() {
        return snackAdult;
    }

    public void setSnackAdult(double snackAdult) {
        this.snackAdult = snackAdult;
    }

    @Override
    public String toString() {
        return "CreditableTable{" +
                "item=" + id +
                ", categoryId=" + categoryId +
                ", Breakfast | Ages 1-2=" + breakfastAgesOneToTwo +
                ", Breakfast | Ages 3-5=" + breakfastAgesThreeToFive +
                ", Breakfast | Ages 6-18=" + breakfastAgesSixToEighteen +
                ", Breakfast | Adult=" + breakfastAdult +
                ", Lunch/Supper | Ages 1-2=" + lunchSupperAgesOneToTwo +
                ", Lunch/Supper | Ages 3-5=" + lunchSupperAgesThreeToFive +
                ", Lunch/Supper | Ages 6-18=" + lunchSupperAgesSixToEighteen +
                ", Lunch/Supper | Adult=" + lunchSupperAdult +
                ", Snack | Ages 1-2=" + snackAgesOneToTwo +
                ", Snack | Ages 3-5=" + snackAgesThreeToFive +
                ", Snack | Ages 6-18=" + snackAgesSixToEighteen +
                ", Snack | Adult=" + snackAdult +
                '}';
    }
}
