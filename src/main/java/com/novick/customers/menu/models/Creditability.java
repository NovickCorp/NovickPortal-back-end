package com.novick.customers.menu.models;

public class Creditability {

    private int categoryId;
    private double score;

    public Creditability(int categoryId, double value, double target) {
        this.categoryId = categoryId;
        this.score = target == 0.0 ? 1.0 : value / target;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getScore() {
        return score;
    }
}
