package com.novick.customers.landing.models;

import java.util.List;
import java.util.Objects;

public record MenuBuilder(String title, List<Section> items) {

    public MenuBuilder {
        Objects.requireNonNull(title);
        Objects.requireNonNull(items);
    }

}
