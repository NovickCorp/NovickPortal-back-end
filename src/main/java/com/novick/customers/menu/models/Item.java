package com.novick.customers.menu.models;

import com.novick.customers.menu.entities.ServingSize;

import java.util.List;
import java.util.Objects;

public record Item(String id, String name, List<Options> options) {

    public Item {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(options);
    }

}
