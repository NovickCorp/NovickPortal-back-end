package com.novick.customers.menu.models;

import java.util.List;
import java.util.Objects;

public record Item(int id, String item, String name, List<Options> options) {

    public Item {
        Objects.requireNonNull(item);
        Objects.requireNonNull(name);
        Objects.requireNonNull(options);
    }

}
