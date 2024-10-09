package com.novick.customers.models;

import java.util.Objects;

public record IdValue(int id, String value) {

    public IdValue {
        if (id < 1) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        Objects.requireNonNull(value);
    }

}
