package com.novick.customers.landing.models;

import java.util.List;
import java.util.Objects;

public record Section(String title, List<TitleDescription> items) {

    public Section {
        Objects.requireNonNull(title);
        Objects.requireNonNull(items);
    }

}
