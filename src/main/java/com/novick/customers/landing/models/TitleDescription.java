package com.novick.customers.landing.models;

import java.util.Objects;

public record TitleDescription(String id, String title, String description) {

    public TitleDescription {
        Objects.requireNonNull(id);
        Objects.requireNonNull(title);
        Objects.requireNonNull(description);
    }

}
