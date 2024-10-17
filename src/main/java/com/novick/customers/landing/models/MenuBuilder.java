package com.novick.customers.landing.models;

import java.util.List;
import java.util.Objects;

public record MenuBuilder(String title, Section steps) {

    public MenuBuilder {
        Objects.requireNonNull(title);
        Objects.requireNonNull(steps);
    }

}
