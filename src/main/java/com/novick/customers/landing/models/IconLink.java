package com.novick.customers.landing.models;

import java.util.Objects;

public record IconLink(String url, String text, String icon, String alt) {

    public IconLink {
        Objects.requireNonNull(url);
        Objects.requireNonNull(text);
        Objects.requireNonNull(icon);
        Objects.requireNonNull(alt);
    }

}
