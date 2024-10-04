package com.novick.customers.menu.models;

import java.util.Objects;

public record Credibility(String category, double score) {

    public Credibility {
        Objects.requireNonNull(category);
    }

}
