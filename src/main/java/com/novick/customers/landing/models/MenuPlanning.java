package com.novick.customers.landing.models;

import java.util.List;
import java.util.Objects;

public record MenuPlanning(String title, List<IconLink> menuLinks) {

    public MenuPlanning {
        Objects.requireNonNull(title);
        Objects.requireNonNull(menuLinks);
    }

}
