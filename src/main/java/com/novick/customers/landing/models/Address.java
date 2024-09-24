package com.novick.customers.landing.models;

import java.util.Objects;

public record Address(String address, String city, String state, String zipCode, String phoneNumber) {

    public Address {
        Objects.requireNonNull(address);
        Objects.requireNonNull(city);
        Objects.requireNonNull(state);
        Objects.requireNonNull(zipCode);
        Objects.requireNonNull(phoneNumber);
    }

}
