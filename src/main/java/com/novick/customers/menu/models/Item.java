package com.novick.customers.menu.models;

import com.novick.customers.menu.entities.ServingSize;

import java.util.List;
import java.util.Objects;

public record Item(String id, String name, Double minimumServingSize, String unitOfMeasure, List<Options> options) {

    public Item {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(options);

        if (minimumServingSize != null && minimumServingSize < 0.0) {
            throw new IllegalArgumentException("minimumServingSize cannot be negative. Current value: " + minimumServingSize);
        }
    }

    public static Item from(ServingSize servingSize, String unitOfMeasure, List<Options> options) {
        return new Item(servingSize.getItem(), servingSize.getDescription(), servingSize.getMinimumViableServingSize(), unitOfMeasure, options);
    }
}
