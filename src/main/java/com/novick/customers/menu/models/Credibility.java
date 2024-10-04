package com.novick.customers.menu.models;

public class Credibility {

    private String category;
    private double score;

    public Credibility(String category, double value, double target) {
        this.category = category;
        this.score = target == 0.0 ? 1.0 : value / target;
    }

    public String getCategory() {
        return category;
    }

    public double getScore() {
        return score;
    }
}
